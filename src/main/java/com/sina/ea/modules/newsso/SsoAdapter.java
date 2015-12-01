package com.sina.ea.modules.newsso;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.servlet.http.Cookie;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.pem.PemReader;


public class SsoAdapter {

    public static final String MD5 = "MD5";

    public static final String RSA = "RSA";

    // cookie

    /** 未加密的用户信息 */
    public static final String COOKIE_KEY_SUP = "SUP";

    /** 加密的用户信息 */
    public static final String COOKIE_KEY_SUE = "SUE";

    // SUP

    public static final String SUP_KEY_COOKIE_VERSION = "cv";

    public static final String SUP_COOKIE_VERSION_VALUE = "1";

    public static final String SUP_KEY_UID = "uid";

    public static final String SUP_KEY_USER = "user";

    public static final String SUP_KEY_BEGIN_TIME = "bt";

    public static final String SUP_KEY_EXPIRED_TIME = "et";

    // SUE

    /** 0 */
    public static final String SUE_KEY_RSA_VERSION = "rv";

    public static final String SUE_KEY_RSA_STRING = "rs";

    private static SsoAdapter INSTANCE;

    static {
        StringWriter string = new StringWriter();
        PrintWriter writer = new PrintWriter(string);
        writer.println("-----BEGIN PUBLIC KEY-----");
        writer.println("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDuRbH5Nj/E+KcPO2cqDyb/QiYy");
        writer.println("XCQh2bR31X/K2EyaqtAveGwvQLIum5yG9PrCjfwaMLnI4pRIufAR0jfAyS+riGwx");
        writer.println("WHvQoB0mgt2dBwzf59jTJBriGrMgC5ZhTCAVLPnRmBQsZ//ArMZOHeULn4x4pK8o");
        writer.println("V23eAA6wHPOLwkchHQIDAQAB");
        writer.println("-----END PUBLIC KEY-----");
        writer.flush();
        byte[] publicKey;
        try {
            publicKey = readPem(new StringReader(string.toString()));
            INSTANCE = new SsoAdapter(publicKey);
        }
        catch (IOException e) {
        }
    }

    /** 公钥 */
    private byte[] publicKey;

    private static ThreadLocal<MessageDigest> MD5_LOCAL = new ThreadLocal<MessageDigest>() {

	    protected MessageDigest initialValue() {
	    	try {
	            return MessageDigest.getInstance(MD5);
	        }
	        catch (NoSuchAlgorithmException e) {
	        }
	        return null;
	    };
        
    };

    public SsoAdapter(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public static SsoAdapter defaultInstance() {
        return INSTANCE;
    }

    public static byte[] readPem(Reader reader) throws IOException {
        PemReader pemReader = new PemReader(reader);
        byte[] bytes = pemReader.readPemObject().getContent();
        pemReader.close();
        return bytes;
    }

    /**
     * rsa 解密校验
     * 
     * @param cookies
     * @return
     */
    public SsoLoginResult cookieLogin(Cookie[] cookies) {
        long second = System.currentTimeMillis() / 1000L;
        String sup = null;
        Map<String, String> supMap = Collections.emptyMap();
        Map<String, String> sueMap = Collections.emptyMap();
        boolean cookieVersionValid = false;
        try {
            for (Cookie cookie : cookies) {
                if (COOKIE_KEY_SUP.equals(cookie.getName())) {
                    sup = URLDecoder.decode(cookie.getValue(), "UTF-8");
                    supMap = parseQuery(sup);
                    if (SUP_COOKIE_VERSION_VALUE.equals(supMap.get(SUP_KEY_COOKIE_VERSION))) {
                        cookieVersionValid = true;
                    }
                }
                if (COOKIE_KEY_SUE.equals(cookie.getName())) {
                    sueMap = parseQuery(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                }
            }
            if (!cookieVersionValid) {
                return failureResult("cookie版本无效");
            }
            Long expiredTime = 0l;//Optional.ofNullable(supMap.get(SUP_KEY_EXPIRED_TIME)).map(Long::valueOf).orElse(0L);
            String expiredTimeStr = supMap.get(SUP_KEY_EXPIRED_TIME);
            if (expiredTimeStr != null){
            	expiredTime = Long.valueOf(expiredTimeStr);
            }
            if (expiredTime < second) {
                return failureResult("SSO登录状态已过期");
            }
            String rsaVersion = sueMap.get(SUE_KEY_RSA_VERSION);
            if(!"0".equals(rsaVersion)){
            	return failureResult("cookie加密版本无效");
            }
//            if (!Objects.equals(rsaVersion, "0")) {
//                return failureResult("cookie加密版本无效");
//            }
            byte[] crypted = Base64.decodeBase64(sueMap.get(SUE_KEY_RSA_STRING + rsaVersion));
            if (crypted.length == 0) {
                return failureResult("cookie缺少加密信息");
            }
            String encodedSup = URLEncoder.encode(sup, "UTF-8");

            String supMd5 = md5(encodedSup);
            byte[] decrypted = decrypt(crypted, publicKey);
            if (supMd5.equals(new String(decrypted, Charset.forName("ISO-8859-1")))) {
                return successResult(new SsoSessionImpl(
                        Long.valueOf(supMap.get(SUP_KEY_UID)),
                        supMap.get(SUP_KEY_USER),
                        Long.valueOf(supMap.get(SUP_KEY_BEGIN_TIME)),
                        Long.valueOf(supMap.get(SUP_KEY_EXPIRED_TIME))));
            }
            else {
                return failureResult("cookie加密信息无效");
            }
        }
        catch (Exception e) {
            return failureResult("SSO登录失败" + e.getMessage());
        }
    }

    private static Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<String, String>();
        for (NameValuePair pair : URLEncodedUtils.parse(query, Charset.forName("UTF-8"))) {
            map.put(pair.getName(), pair.getValue());
        }
        return map;
    }

    private static String md5(String string) {
        byte[] md5 = MD5_LOCAL.get().digest(string.getBytes());
        return new String(Hex.encode(md5));
    }

    private static byte[] decrypt(byte[] bytes, byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PublicKey rsaPublicKey = keyFactory.generatePublic(new X509EncodedKeySpec(key));
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, rsaPublicKey);
            return cipher.doFinal(bytes);
        }
        catch (Exception e) {
            return new byte[0];
        }

    }

    private static SsoLoginResult successResult(SsoSession session) {
        SsoLoginResultImpl result = new SsoLoginResultImpl();
        result.session = session;
        result.message = null;
        return result;
    }

    private static SsoLoginResult failureResult(String message) {
        SsoLoginResultImpl result = new SsoLoginResultImpl();
        result.message = message;
        result.session = null;
        return result;
    }

    private static class SsoLoginResultImpl implements SsoLoginResult {

        private String message;

        private SsoSession session;

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public SsoSession getSession() {
            return session;
        }

    }

    private static class SsoSessionImpl implements SsoSession {

        private Long uid;

        private String user;

        private Long beginTime;

        private Long expiredTime;

        private SsoSessionImpl(Long uid, String user, Long beginTime, Long expiredTime) {
            this.uid = uid;
            this.user = user;
            this.beginTime = beginTime;
            this.expiredTime = expiredTime;
        }

        @Override
        public Long getUid() {
            return uid;
        }

        @Override
        public String getUser() {
            return user;
        }

        @Override
        public Long getBeginTime() {
            return beginTime;
        }

        @Override
        public Long getExpiredTime() {
            return expiredTime;
        }
    }

}
