package com.sina.ea.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sina.ea.utils.EAHttp;
import com.sina.adm.base.utils.LogUtils;

public class EAHttp {

	private static final Logger logger = LoggerFactory.getLogger("eahttp");

	public static final int HTTP_CONNECT_TIMEOUT = 8000;
	public static final int HTTP_READ_TIMEOUT = 10000;
	public static final int retryTimes = 1;

	public static String getTextByHttp(String url) {
		return getTextByHttp(url, HTTP_CONNECT_TIMEOUT, HTTP_READ_TIMEOUT);
	}

	/**
	 *
	 * #func 支持可以设置超时时间的请求<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	public static String getTextByHttp(String url, int connectTimeOut,
			int readTimeOut) {
		if (connectTimeOut <= 0 || readTimeOut <= 0) {
			connectTimeOut = HTTP_CONNECT_TIMEOUT;
			readTimeOut = HTTP_READ_TIMEOUT;
		}
		String retStr = null;
		HttpURLConnection conn = null;
		InputStream inStr = null;
		BufferedReader in = null;
		
		long s1 = System.currentTimeMillis();
		
		try {
			
			URL httpURL = new URL(url);
			conn = (HttpURLConnection) (httpURL.openConnection());
			conn.setConnectTimeout(connectTimeOut); // 连接超时
			conn.setReadTimeout(readTimeOut); // 读操作超时
			inStr = conn.getInputStream();

			in = new BufferedReader(new InputStreamReader(inStr));
			StringBuilder xmlContentBuf = new StringBuilder(300);
			String inputLin = null;
			while ((inputLin = in.readLine()) != null) {
				xmlContentBuf.append(inputLin);
			}

			retStr = xmlContentBuf.toString();
			
			logger.debug(url + " htmlContent: " + retStr);
			return retStr;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (inStr != null) {
					inStr.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			Long s2 = System.currentTimeMillis();
			logger.info("eq: " + url + "" + " QT:" + (s2-s1));
		}
		return null;
	}

	/**
	 *
	 * #func 通过post请求url，并获取返回值<br>
	 * #desc 带轮询和重试机制，轮询使用随即轮询,默认采用utf-8
	 *
	 * @author zhangzhibo
	 * @version 4.0.3
	 */
	public static String getTextByHttpWithPostWay(String[] urls,
			Map<String, String> params) {
		return getTextByHttpWithPostWay(urls, params, HTTP_CONNECT_TIMEOUT,
				HTTP_READ_TIMEOUT);
	}

	/**
	 *
	 * #func 通过post请求url，并获取返回值<br>
	 * #desc 带轮询和重试机制，轮询使用随即轮询,默认采用utf-8
	 *
	 * @author zhangzhibo
	 * @version 4.0.3
	 */
	public static String getTextByHttpWithPostWay(String[] urls,
			Map<String, String> params, int connectTimeOut, int readTimeOut) {
		if (urls == null || urls.length == 0 || params == null
				|| params.size() == 0) {
			return null;
		}
		if (connectTimeOut <= 0 || readTimeOut <= 0) {
			connectTimeOut = HTTP_CONNECT_TIMEOUT;
			readTimeOut = HTTP_READ_TIMEOUT;
		}
		Random r = new Random();
		int index = r.nextInt(urls.length);

		for (int i = 0; i < retryTimes * urls.length; i++) {
			String url = urls[index];

			String retStr = null;
			HttpURLConnection conn = null;
			InputStream inStr = null;
			BufferedReader in = null;
			
			long s1 = System.currentTimeMillis();
			
			try {
				
				URL httpURL = new URL(url);
				conn = (HttpURLConnection) (httpURL.openConnection());
				conn.setConnectTimeout(connectTimeOut); // 连接超时
				conn.setReadTimeout(readTimeOut); // 读操作超时

				// app方面需要请求是POST的
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Charset", "UTF-8");
				// 必须开启能写入output，否则下面无法写入内容
				conn.setDoOutput(true);

				// 添加post参数
				String param = "";
				for (Map.Entry<String, String> key : params.entrySet()) {
					param += key.getKey() + "="
							+ URLEncoder.encode(key.getValue(), "utf-8") + "&";
				}
				if (!param.equals("")) {
					param = param.substring(0, param.lastIndexOf('&'));
				}
				// System.out.println(param);

				conn.getOutputStream().write(param.getBytes());

				conn.getOutputStream().flush();
				conn.getOutputStream().close();

				inStr = conn.getInputStream();

				in = new BufferedReader(new InputStreamReader(inStr));
				StringBuilder xmlContentBuf = new StringBuilder(300);
				String inputLin = null;
				while ((inputLin = in.readLine()) != null) {
					xmlContentBuf.append(inputLin);
				}

				retStr = xmlContentBuf.toString();
				
				logger.debug(url + " htmlContent: " + retStr);
				return retStr;
			} catch (MalformedURLException e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",MalformedURLException error:" + e);
			} catch (Exception e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",IOException error:" + e);
			} finally {
				index = (index + 1) % urls.length;
				try {
					if (in != null) {
						in.close();
					}
					if (inStr != null) {
						inStr.close();
					}
				} catch (IOException e) {
					logger.error("[repeat times:" + i + "],now index:" + index
							+ ",url:" + url + ",finally IOException error:" + e);
				}
				Long s2 = System.currentTimeMillis();
				logger.info("eq: " + url + "" + " QT:" + (s2-s1));
			}
		}
		LogUtils.error(logger, "no success connect  after repeat 3 times",
				new Exception("no success connect after repeat 3 times"));
		return null;
	}

	/**
	 *
	 * #func 通过post请求url，并获取返回值<br>
	 * #desc 带轮询和重试机制，轮询使用随即轮询，采用gbk
	 *
	 * @author zhangzhibo
	 * @version 4.0.3
	 */
	public static String getTextByHttpWithPostWayByGBK(String[] urls,
			Map<String, String> params) {
		if (urls == null || urls.length == 0 || params == null
				|| params.size() == 0) {
			return null;
		}

		Random r = new Random();
		int index = r.nextInt(urls.length);

		for (int i = 0; i < retryTimes * urls.length; i++) {
			String url = urls[index];

			String retStr = null;
			HttpURLConnection conn = null;
			InputStream inStr = null;
			BufferedReader in = null;
			
			long s1 = System.currentTimeMillis();
			
			try {
				
				URL httpURL = new URL(url);
				conn = (HttpURLConnection) (httpURL.openConnection());
				conn.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // 连接超时
				conn.setReadTimeout(HTTP_READ_TIMEOUT); // 读操作超时

				// app方面需要请求是POST的
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Charset", "GBK");

				// 必须开启能写入output，否则下面无法写入内容
				conn.setDoOutput(true);

				// 添加post参数
				String param = "";
				for (Map.Entry<String, String> key : params.entrySet()) {
					param += key.getKey() + "="
							+ URLEncoder.encode(key.getValue(), "gbk") + "&";
				}
				if (!param.equals("")) {
					param = param.substring(0, param.lastIndexOf('&'));
				}

				conn.getOutputStream().write(param.getBytes());

				conn.getOutputStream().flush();
				conn.getOutputStream().close();

				inStr = conn.getInputStream();

				in = new BufferedReader(new InputStreamReader(inStr));
				StringBuilder xmlContentBuf = new StringBuilder(300);
				String inputLin = null;
				while ((inputLin = in.readLine()) != null) {
					xmlContentBuf.append(inputLin);
				}

				retStr = xmlContentBuf.toString();
				
				logger.debug(url + " htmlContent: " + retStr);
				return retStr;
			} catch (MalformedURLException e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",MalformedURLException error:" + e);
			} catch (Exception e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",IOException error:" + e);
			} finally {
				index = (index + 1) % urls.length;
				try {
					if (in != null) {
						in.close();
					}
					if (inStr != null) {
						inStr.close();
					}
				} catch (IOException e) {
					logger.error("[repeat times:" + i + "],now index:" + index
							+ ",url:" + url + ",finally IOException error:" + e);
				}
				Long s2 = System.currentTimeMillis();
				logger.info("eq: " + url + "" + " QT:" + (s2-s1));
			}
		}
		LogUtils.error(logger, "no success connect after repeat 3 times",
				new Exception("no success connect after repeat 3 times"));
		return null;
	}

	/**
	 *
	 * #func 通过post请求url，并获取返回值，在请求中带上cookie，用于发送cookie给php用于解析sso信息<br>
	 * #desc 带轮询和重试机制，轮询使用随即轮询,默认采用utf-8
	 *
	 * @author zhangzhibo
	 * @version 4.0.3
	 */
	public static String getTextByHttpWithPostWayAndSendCookie(String[] urls,
			Map<String, String> params, Cookie[] cookies) {
		if (urls == null || urls.length == 0) {
			return null;
		}

		Random r = new Random();
		int index = r.nextInt(urls.length);

		for (int i = 0; i < retryTimes * urls.length; i++) {
			String url = urls[index];

			String retStr = null;
			HttpURLConnection conn = null;
			InputStream inStr = null;
			BufferedReader in = null;
			
			long s1 = System.currentTimeMillis();
			
			try {
				
				URL httpURL = new URL(url);
				conn = (HttpURLConnection) (httpURL.openConnection());
				conn.setConnectTimeout(HTTP_CONNECT_TIMEOUT); // 连接超时
				conn.setReadTimeout(HTTP_READ_TIMEOUT); // 读操作超时

				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				conn.setRequestProperty("Charset", "UTF-8");

				// 将cookie放置到头部
				if (cookies != null && cookies.length > 0) {
					String cookiesStr = "";
					StringBuffer sb = new StringBuffer();
					for (Cookie cookie : cookies) {
						sb.append(cookie.getName() + "=" + cookie.getValue()
								+ ";");
					}
					if (sb.length() > 0) {
						cookiesStr = sb.toString();
					}
					conn.setRequestProperty("Cookie", cookiesStr);
				}

				// 必须开启能写入output，否则下面无法写入内容
				conn.setDoOutput(true);

				// 添加post参数
				String param = "";
				if (params != null && params.size() > 0) {
					for (Map.Entry<String, String> key : params.entrySet()) {
						param += key.getKey() + "="
								+ URLEncoder.encode(key.getValue(), "utf-8")
								+ "&";
					}
				}
				if (!param.equals("")) {
					param = param.substring(0, param.lastIndexOf('&'));
				}

				conn.getOutputStream().write(param.getBytes());

				conn.getOutputStream().flush();
				conn.getOutputStream().close();

				inStr = conn.getInputStream();

				in = new BufferedReader(new InputStreamReader(inStr));
				StringBuilder xmlContentBuf = new StringBuilder(300);
				String inputLin = null;
				while ((inputLin = in.readLine()) != null) {
					xmlContentBuf.append(inputLin);
				}

				retStr = xmlContentBuf.toString();
				
				logger.debug(url + " htmlContent: " + retStr);
				return retStr;
			} catch (MalformedURLException e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",MalformedURLException error:" + e);
			} catch (Exception e) {
				logger.error("[repeat times:" + i + "],now index:" + index
						+ ",url:" + url + ",IOException error:" + e);
			} finally {
				index = (index + 1) % urls.length;
				try {
					if (in != null) {
						in.close();
					}
					if (inStr != null) {
						inStr.close();
					}
				} catch (IOException e) {
					logger.error("[repeat times:" + i + "],now index:" + index
							+ ",url:" + url + ",finally IOException error:" + e);
				}
				Long s2 = System.currentTimeMillis();
				logger.info("eq: " + url + "" + " QT:" + (s2-s1));
			}
		}
		LogUtils.error(logger, "no success connect after repeat 3 times",
				new Exception("no success connect after repeat 3 times"));
		return null;
	}

	/**
	 *
	 * #func post数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String post(String url, Map<String, String> params) {
		return post(new String[] { url }, params, HTTP.UTF_8);
	}

	/**
	 *
	 * #func post数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String post(String[] urls, Map<String, String> params) {
		return post(urls, params, HTTP.UTF_8);
	}

	/**
	 *
	 * #func post数据<br>
	 * #desc urls是一个字符串，包括多个url，以splitChar分隔
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String post(String urls, Map<String, String> params,
			String splitChar) {
		return post(urls.split(splitChar), params, HTTP.UTF_8);
	}

	/**
	 *
	 * #func post数据，设置默认超时时间<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String post(String[] urls, Map<String, String> params,
			String encoding) {
		return post(urls, params, encoding, 1, HTTP_CONNECT_TIMEOUT,
				HTTP_READ_TIMEOUT);
	}

	/**
	 *
	 * #func post数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String post(String[] urls, Map<String, String> params,
			String encoding, int retryTimes, int connectionTimeoutMillis,
			int socketTimeoutMillis) {

		if (urls == null || urls.length == 0) {
			return null;
		}

		Random r = new Random();
		int index = r.nextInt(urls.length);
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		// HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),
		// connectionTimeoutMillis);
		// HttpConnectionParams.setSoTimeout(httpclient.getParams(),
		// socketTimeoutMillis);

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		if (params != null && params.size() > 0) {
			for (Map.Entry<String, String> key : params.entrySet()) {
				list.add(new BasicNameValuePair(key.getKey(), key.getValue()));
			}
		}

		for (int i = 0; i < retryTimes * urls.length; i++) {
			String url = urls[index];
			Long s1 = System.currentTimeMillis();
			String requestLine = null;
			try {
				
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));
				if (connectionTimeoutMillis > 0) {
					httpPost.getParams().setParameter(
							CoreConnectionPNames.CONNECTION_TIMEOUT,
							connectionTimeoutMillis);
				}
				if (socketTimeoutMillis > 0) {
					httpPost.getParams().setParameter(
							CoreConnectionPNames.SO_TIMEOUT,
							socketTimeoutMillis);
				}
				
				HttpResponse response = httpclient.execute(httpPost);
				HttpEntity resEntity = response.getEntity();
				
				requestLine = httpPost.getRequestLine().toString();
				
				System.out.println(response.getStatusLine());
				
				String content = null;
				if (resEntity != null) {
					// logger.info("testContentEncoding================"+resEntity.getContentEncoding().getValue());
					content = EntityUtils.toString(resEntity, encoding);
					// logger.info("testContent================"+content);
					resEntity.consumeContent();
					return content;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				index = (index + 1) % urls.length;
				
				Long s2 = System.currentTimeMillis();
				logger.info("eq: " + url + "" + " QT:" + (s2-s1));
			}
		}
		try {
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * #func get数据<br>
	 * #desc urls是一个字符串，包括多个url，以splitChar分隔
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String urls, String splitChar) {
		return get(urls.split(splitChar), HTTP.UTF_8);
	}

	/**
	 *
	 * #func get数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String[] urls) {
		return get(urls, HTTP.UTF_8);
	}

	/**
	 *
	 * #func get数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String url) {
		return get(new String[] { url }, HTTP.UTF_8);
	}

	/**
	 *
	 * #func get数据，设置默认超时时间<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String[] urls, String encoding) {
		return get(urls, encoding, HTTP_CONNECT_TIMEOUT, HTTP_READ_TIMEOUT);
	}

	/**
	 *
	 * #func get数据，设置默认超时时间<br>
	 * #desc urls是一个字符串，包括多个url，以splitChar分隔
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String urls, String encoding, String splitChar) {
		return get(urls.split(splitChar), encoding, HTTP_CONNECT_TIMEOUT,
				HTTP_READ_TIMEOUT);
	}

	/**
	 *
	 * #func post数据<br>
	 * #desc
	 *
	 * @author zhangzhibo
	 * @version 1.0.1
	 */
	public static String get(String[] urls, String encoding,
			int connectionTimeoutMillis, int socketTimeoutMillis) {
		if (urls == null || urls.length == 0) {
			return null;
		}

		Random r = new Random();
		int index = r.nextInt(urls.length);
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		// HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),
		// connectionTimeoutMillis);
		// HttpConnectionParams.setSoTimeout(httpclient.getParams(),
		// socketTimeoutMillis);

		for (int i = 0; i < retryTimes * urls.length; i++) {
			String url = urls[index];
			
			long s1 = System.currentTimeMillis();
			String requestLine = null;
			
			try {
				
				HttpGet httpGet = new HttpGet(url);
				if (connectionTimeoutMillis > 0) {
					httpGet.getParams().setParameter(
							CoreConnectionPNames.CONNECTION_TIMEOUT,
							connectionTimeoutMillis);
				}
				if (socketTimeoutMillis > 0) {
					httpGet.getParams().setParameter(
							CoreConnectionPNames.SO_TIMEOUT,
							socketTimeoutMillis);
				}
				HttpResponse response = httpclient.execute(httpGet);
				HttpEntity resEntity = response.getEntity();

				requestLine = httpGet.getRequestLine().toString();
				
				System.out.println(response.getStatusLine());
				
				String content = null;
				if (resEntity != null) {
					content = EntityUtils.toString(resEntity, encoding);
					resEntity.consumeContent();
					return content;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				index = (index + 1) % urls.length;
				Long s2 = System.currentTimeMillis();
				logger.info("eq: " + url + " QT:" + (s2-s1));
			}
		}
		try {
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// System.out.println(HttpTools.getTextByHttp("http://img.adbox.sina.com.cn/ad/3971.html"));

		// ssoId=, adboxId:963, adboxStr:null
		// System.out.println(HttpTools.getTextByHttp("http://adbox.sina.com.cn/api/templete?ssoId=2832020747&id=963"));

		String checkSSOURL = "http://ilogin.sina.com.cn/api/amisso.php";

		String entry = "adservice";
		String pin = "10928d4dc84238f6c1046ac27512f697";
		String user = "saxtest@sina.com";
		String caller = "/usr/local/openresty/nginx/html/";
		String m = "bdf3f838c6da20e1c3f0f7d582060a32";
		// String m ="3d88cc7857be2e96446108b3fd176d32";
		String ag = "0";

		Map<String, String> params = new HashMap<String, String>();
		// params.put("entry", entry);
		// params.put("user", user);
		// params.put("m", m);
		// params.put("caller", caller);
		// params.put("ag", ag);
		params.put("json",
				"{\"ideaId\":1,\"auditStatus\":2,\"refuseContent\":\"中文wabc\"}");

		String url = "http://amp.ad.sina.com.cn:8080/ea/api/ideaAudit.action";
		// System.out.println(HttpTools.getTextByHttpWithPostWay(new
		// String[]{url}, params));

		// System.out.println(HttpTools.post(new String[]{url}, params));
		String getUrl = "http://www.baidu.com";
		System.out.println(EAHttp.get(getUrl));
	}
}
