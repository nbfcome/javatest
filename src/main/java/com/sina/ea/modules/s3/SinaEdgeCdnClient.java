package com.sina.ea.modules.s3;

import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.sina.ea.utils.EAHttp;
import com.sina.adm.base.utils.JSONUtils;


public class SinaEdgeCdnClient {
	static {
		sinaEdgekid = S3Utils.getSinaEdgeKid();
		sinaEdgeKey = S3Utils.getSinaEdgeKey();
	}
	private static class S3ClientHolder {
		private static SinaEdgeCdnClient resource = new SinaEdgeCdnClient();
	}
	public static SinaEdgeCdnClient getInstanse() {
		return S3ClientHolder.resource;
	}
	private static String algo = "HmacSHA1";
	private static String sinaEdgekid;
	private static String sinaEdgeKey;
	
	/**
	 * 
	 * #func String的trim会把空格、tab、回车等都去掉，不能使用StringUtils.trimToEmpty<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	private static String checkNull(String s){
		if(s == null)
			return "";
		else
			return s;
	}
	
	private static String hmac_sha1(String data, String key) {
		byte[] byteHMAC = null;
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), algo);
			Mac mac = Mac.getInstance(algo);
			mac.init(signingKey);
			byteHMAC = mac.doFinal(data.getBytes());
			return new String(new String(byteHMAC, "iso-8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String makeQueryString(Map<String,String> data){
		if(data== null || data.size()==0){
			return null;
		}
		try{
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : data.entrySet()){
				sb.append(entry.getKey()+"="+entry.getValue()+"&");
			}
			if(sb.length()>0){
				sb.delete(sb.length()-1, sb.length());
			}
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	private static String submit(String url, Map<String,String> args){
		
		System.out.println(url);
		System.out.println(args);
		
		Map<String,String> localArgs = new TreeMap<String,String>();
		Map<String,String> newArgs = new TreeMap<String,String>();
		try{
			String httpVerb = "POST";
			URL urlObject = new URL(url);
			String host = urlObject.getHost();
			String uri = urlObject.getPath();
			String query = urlObject.getQuery();
			System.out.println(host);
			System.out.println(uri);
			System.out.println(query);
			
			if(StringUtils.isNotBlank(query)){
				String[] arrays = query.split("&");
				for(String array : arrays){
					String[] units = array.split("=");
					String key = units[0];
					String value = "";
					if(units.length>1){
						value = units[1];
					}
					newArgs.put(key, value);
				}
			}
			localArgs.putAll(args);
			localArgs.putAll(newArgs);
			
			if(localArgs.get("timestamp") == null){
				String timestamp = String.valueOf(System.currentTimeMillis()/1000);
				localArgs.put("timestamp", timestamp);
				args.put("timestamp", timestamp);
			}
			
			String queryString = makeQueryString(localArgs);
			System.out.println(queryString);
			
			String stringToSign = checkNull(httpVerb) + "\n"
						        + checkNull(host) + "\n"
						        + checkNull(uri) + "\n"
						        + checkNull(queryString);
			System.out.println(stringToSign);
			
			String signature = hmac_sha1(stringToSign, sinaEdgeKey);
			String encodedBase64 = new String(Base64.encodeBase64(signature
					.getBytes("ISO-8859-1")));
			System.out.println("signature="+encodedBase64);
			signature = encodedBase64.substring(5, 15);
			args.put("ssig", signature);
			System.out.println("ssig="+signature);
			
			String content = EAHttp.getTextByHttpWithPostWay(new String[]{url}, args);
//			for(Map.Entry<String, String> entry : args.entrySet()){
//				System.out.println(entry.getKey()+":"+entry.getValue());
//			}
			System.out.println("content="+content);
			return content;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Boolean purgeSinaEdge(String[] urls){
		String url = "http://connect.sinaedge.com/object/purge";
		String jsonUrl = JSONUtils.toJson(urls);
		Map<String,String> args = new TreeMap<String,String>();
		args.put("kid", sinaEdgekid);
		args.put("url", jsonUrl);
		String content = submit(url,args);
		TypeToken<Map<String,String>> token = new TypeToken<Map<String,String>>(){};
		Map<String,String> map = JSONUtils.fromJson(content, token);
		if(map == null || map.get("code") == null || !"0".equals(map.get("code"))){
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		String[] urls = new String[]{"http://img.amp.ad.sina.com.cn/http://img.amp.ad.sina.com.cnamp/test/1351307833242.jpg"};
		//String[] urls = new String[]{"http://img.amp.ad.sina.com.cn/sax/cputest/15.swf",
		//		"http://img.amp.ad.sina.com.cn/sax/cputest/14.swf"};
		Boolean flag = SinaEdgeCdnClient.purgeSinaEdge(urls);
		System.out.println(flag);
		
		String json = "{\"status\": 0, \"message\": \"\", \"code\": 0, \"result\": \"\"}";
		TypeToken<Map<String,Object>> token = new TypeToken<Map<String,Object>>(){};
		Map<String,Object> map = JSONUtils.fromJson(json, token);
		for(Map.Entry<String, Object> entry : map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
