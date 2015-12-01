package com.sina.ea.modules.s3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sina.ea.Variables;


public class S3Client {
	static {
		accessKey = S3Utils.getAccessKey();
		secretKey = S3Utils.getSecretKey();
		bucket = S3Utils.getBucket();
		domain = S3Utils.getDomain();
	}
	private static class S3ClientHolder {
		private static S3Client resource = new S3Client();
	}
	public static S3Client getInstanse() {
		return S3ClientHolder.resource;
	}
	private static String algo = "HmacSHA1";
	private static String accessKey;
	private static String secretKey;
	private static String bucket;
	private static String domain;
	private static String[] extraArray = {"copy", "acl", "location", "logging", "relax", "meta", "torrent", "uploadID", "ip"};
	private static int HTTP_200_OK = 200;
	private static int HTTP_204_NO_CONTENT = 204;
	
	private static Map<String,String> contentTypeMap = new HashMap<String,String>();
	
	static {
		contentTypeMap.put(".*", "application/octet-stream");
		contentTypeMap.put(".001", "application/x-001");
		contentTypeMap.put(".301", "application/x-301");
		contentTypeMap.put(".323", "text/h323");
		contentTypeMap.put(".906", "application/x-906");
		contentTypeMap.put(".907", "drawing/907");
		contentTypeMap.put(".a11", "application/x-a11");
		contentTypeMap.put(".acp", "audio/x-mei-aac");
		contentTypeMap.put(".ai", "application/postscript");
		contentTypeMap.put(".aif", "audio/aiff");
		contentTypeMap.put(".aifc", "audio/aiff");
		contentTypeMap.put(".aiff", "audio/aiff");
		contentTypeMap.put(".anv", "application/x-anv");
		contentTypeMap.put(".asa", "text/asa");
		contentTypeMap.put(".asf", "video/x-ms-asf");
		contentTypeMap.put(".asp", "text/asp");
		contentTypeMap.put(".asx", "video/x-ms-asf");
		contentTypeMap.put(".au", "audio/basic");
		contentTypeMap.put(".avi", "video/avi");
		contentTypeMap.put(".awf", "application/vnd.adobe.workflow");
		contentTypeMap.put(".biz", "text/xml");
		contentTypeMap.put(".bmp", "application/x-bmp");
		contentTypeMap.put(".bot", "application/x-bot");
		contentTypeMap.put(".c4t", "application/x-c4t");
		contentTypeMap.put(".c90", "application/x-c90");
		contentTypeMap.put(".cal", "application/x-cals");
		contentTypeMap.put(".cat", "application/vnd.ms-pki.seccat");
		contentTypeMap.put(".cdf", "application/x-netcdf");
		contentTypeMap.put(".cdr", "application/x-cdr");
		contentTypeMap.put(".cel", "application/x-cel");
		contentTypeMap.put(".cer", "application/x-x509-ca-cert");
		contentTypeMap.put(".cg4", "application/x-g4");
		contentTypeMap.put(".cgm", "application/x-cgm");
		contentTypeMap.put(".cit", "application/x-cit");
		contentTypeMap.put(".class", "java/*");
		contentTypeMap.put(".cml", "text/xml");
		contentTypeMap.put(".cmp", "application/x-cmp");
		contentTypeMap.put(".cmx", "application/x-cmx");
		contentTypeMap.put(".cot", "application/x-cot");
		contentTypeMap.put(".crl", "application/pkix-crl");
		contentTypeMap.put(".crt", "application/x-x509-ca-cert");
		contentTypeMap.put(".csi", "application/x-csi");
		contentTypeMap.put(".css", "text/css");
		contentTypeMap.put(".cut", "application/x-cut");
		contentTypeMap.put(".dbf", "application/x-dbf");
		contentTypeMap.put(".dbm", "application/x-dbm");
		contentTypeMap.put(".dbx", "application/x-dbx");
		contentTypeMap.put(".dcd", "text/xml");
		contentTypeMap.put(".dcx", "application/x-dcx");
		contentTypeMap.put(".der", "application/x-x509-ca-cert");
		contentTypeMap.put(".dgn", "application/x-dgn");
		contentTypeMap.put(".dib", "application/x-dib");
		contentTypeMap.put(".dll", "application/x-msdownload");
		contentTypeMap.put(".doc", "application/msword");
		contentTypeMap.put(".dot", "application/msword");
		contentTypeMap.put(".drw", "application/x-drw");
		contentTypeMap.put(".dtd", "text/xml");
		//contentTypeMap.put(".dwf", "Model/vnd.dwf");
		contentTypeMap.put(".dwf", "application/x-dwf");
		contentTypeMap.put(".dwg", "application/x-dwg");
		contentTypeMap.put(".dxb", "application/x-dxb");
		contentTypeMap.put(".dxf", "application/x-dxf");
		contentTypeMap.put(".edn", "application/vnd.adobe.edn");
		contentTypeMap.put(".emf", "application/x-emf");
		contentTypeMap.put(".eml", "message/rfc822");
		contentTypeMap.put(".ent", "text/xml");
		contentTypeMap.put(".epi", "application/x-epi");
		//contentTypeMap.put(".eps", "application/x-ps");
		contentTypeMap.put(".eps", "application/postscript");
		contentTypeMap.put(".etd", "application/x-ebx");
		contentTypeMap.put(".exe", "application/x-msdownload");
		contentTypeMap.put(".fax", "image/fax");
		contentTypeMap.put(".fdf", "application/vnd.fdf");
		contentTypeMap.put(".fif", "application/fractals");
		//contentTypeMap.put(".flv", "video/x-flv");
		contentTypeMap.put(".fo", "text/xml");
		contentTypeMap.put(".frm", "application/x-frm");
		contentTypeMap.put(".g4", "application/x-g4");
		contentTypeMap.put(".gbr", "application/x-gbr");
		contentTypeMap.put(".gcd", "application/x-gcd");
		contentTypeMap.put(".gif", "image/gif");
		contentTypeMap.put(".gl2", "application/x-gl2");
		contentTypeMap.put(".gp4", "application/x-gp4");
		contentTypeMap.put(".hgl", "application/x-hgl");
		contentTypeMap.put(".hmr", "application/x-hmr");
		contentTypeMap.put(".hpg", "application/x-hpgl");
		contentTypeMap.put(".hpl", "application/x-hpl");
		contentTypeMap.put(".hqx", "application/mac-binhex40");
		contentTypeMap.put(".hrf", "application/x-hrf");
		contentTypeMap.put(".hta", "application/hta");
		contentTypeMap.put(".htc", "text/x-component");
		contentTypeMap.put(".htm", "text/html");
		contentTypeMap.put(".html", "text/html");
		contentTypeMap.put(".htt", "text/webviewhtml");
		contentTypeMap.put(".htx", "text/html");
		contentTypeMap.put(".icb", "application/x-icb");
		contentTypeMap.put(".ico", "image/x-icon");
		//contentTypeMap.put(".ico", "application/x-ico");
		contentTypeMap.put(".iff", "application/x-iff");
		contentTypeMap.put(".ig4", "application/x-g4");
		contentTypeMap.put(".igs", "application/x-igs");
		contentTypeMap.put(".iii", "application/x-iphone");
		contentTypeMap.put(".img", "application/x-img");
		contentTypeMap.put(".ins", "application/x-internet-signup");
		contentTypeMap.put(".isp", "application/x-internet-signup");
		contentTypeMap.put(".ivf", "video/x-ivf");
		contentTypeMap.put(".java", "java/*");
		contentTypeMap.put(".jfif", "image/jpeg");
		contentTypeMap.put(".jpe", "image/jpeg");
		//contentTypeMap.put(".jpe", "application/x-jpe");
		contentTypeMap.put(".jpeg", "image/jpeg");
		contentTypeMap.put(".jpg", "image/jpeg");
		//contentTypeMap.put(".jpg", "application/x-jpg");
		contentTypeMap.put(".js", "application/x-javascript");
		contentTypeMap.put(".jsp", "text/html");
		contentTypeMap.put(".la1", "audio/x-liquid-file");
		contentTypeMap.put(".lar", "application/x-laplayer-reg");
		contentTypeMap.put(".latex", "application/x-latex");
		contentTypeMap.put(".lavs", "audio/x-liquid-secure");
		contentTypeMap.put(".lbm", "application/x-lbm");
		contentTypeMap.put(".lmsff", "audio/x-la-lms");
		contentTypeMap.put(".ls", "application/x-javascript");
		contentTypeMap.put(".ltr", "application/x-ltr");
		contentTypeMap.put(".m1v", "video/x-mpeg");
		contentTypeMap.put(".m2v", "video/x-mpeg");
		contentTypeMap.put(".m3u", "audio/mpegurl");
		contentTypeMap.put(".m4e", "video/mpeg4");
		contentTypeMap.put(".mac", "application/x-mac");
		contentTypeMap.put(".man", "application/x-troff-man");
		contentTypeMap.put(".math", "text/xml");
		contentTypeMap.put(".mdb", "application/msaccess");
		//contentTypeMap.put(".mdb", "application/x-mdb");
		contentTypeMap.put(".mfp", "application/x-shockwave-flash");
		contentTypeMap.put(".mht", "message/rfc822");
		contentTypeMap.put(".mhtml", "message/rfc822");
		contentTypeMap.put(".mi", "application/x-mi");
		contentTypeMap.put(".mid", "audio/mid");
		contentTypeMap.put(".midi", "audio/mid");
		contentTypeMap.put(".mil", "application/x-mil");
		//contentTypeMap.put(".mml", "text/xml");
		contentTypeMap.put(".mnd", "audio/x-musicnet-download");
		contentTypeMap.put(".mns", "audio/x-musicnet-stream");
		contentTypeMap.put(".mocha", "application/x-javascript");
		contentTypeMap.put(".movie", "video/x-sgi-movie");
		contentTypeMap.put(".mp1", "audio/mp1");
		contentTypeMap.put(".mp2", "audio/mp2");
		contentTypeMap.put(".mp2v", "video/mpeg");
		contentTypeMap.put(".mp3", "audio/mp3");
		contentTypeMap.put(".mp4", "video/mpeg4");
		contentTypeMap.put(".mpa", "video/x-mpg");
		contentTypeMap.put(".mpd", "application/vnd.ms-project");
		contentTypeMap.put(".mpe", "video/x-mpeg");
		contentTypeMap.put(".mpeg", "video/mpg");
		contentTypeMap.put(".mpg", "video/mpg");
		contentTypeMap.put(".mpga", "audio/rn-mpeg");
		contentTypeMap.put(".mpp", "application/vnd.ms-project");
		contentTypeMap.put(".mps", "video/x-mpeg");
		contentTypeMap.put(".mpt", "application/vnd.ms-project");
		contentTypeMap.put(".mpv", "video/mpg");
		contentTypeMap.put(".mpv2", "video/mpeg");
		contentTypeMap.put(".mpw", "application/vnd.ms-project");
		contentTypeMap.put(".mpx", "application/vnd.ms-project");
		//contentTypeMap.put(".mtx", "text/xml");
		contentTypeMap.put(".mxp", "application/x-mmxp");
		contentTypeMap.put(".net", "image/pnetvue");
		contentTypeMap.put(".nrf", "application/x-nrf");
		contentTypeMap.put(".nws", "message/rfc822");
		contentTypeMap.put(".odc", "text/x-ms-odc");
		contentTypeMap.put(".out", "application/x-out");
		contentTypeMap.put(".p10", "application/pkcs10");
		contentTypeMap.put(".p12", "application/x-pkcs12");
		contentTypeMap.put(".p7b", "application/x-pkcs7-certificates");
		contentTypeMap.put(".p7c", "application/pkcs7-mime");
		contentTypeMap.put(".p7m", "application/pkcs7-mime");
		contentTypeMap.put(".p7r", "application/x-pkcs7-certreqresp");
		contentTypeMap.put(".p7s", "application/pkcs7-signature");
		contentTypeMap.put(".pc5", "application/x-pc5");
		contentTypeMap.put(".pci", "application/x-pci");
		contentTypeMap.put(".pcl", "application/x-pcl");
		contentTypeMap.put(".pcx", "application/x-pcx");
		contentTypeMap.put(".pdf", "application/pdf");
		contentTypeMap.put(".pdx", "application/vnd.adobe.pdx");
		contentTypeMap.put(".pfx", "application/x-pkcs12");
		contentTypeMap.put(".pgl", "application/x-pgl");
		contentTypeMap.put(".pic", "application/x-pic");
		contentTypeMap.put(".pko", "application/vnd.ms-pki.pko");
		contentTypeMap.put(".pl", "application/x-perl");
		contentTypeMap.put(".plg", "text/html");
		contentTypeMap.put(".pls", "audio/scpls");
		contentTypeMap.put(".plt", "application/x-plt");
		contentTypeMap.put(".png", "image/png");
		//contentTypeMap.put(".png", "application/x-png");
		contentTypeMap.put(".pot", "application/vnd.ms-powerpoint");
		contentTypeMap.put(".ppa", "application/vnd.ms-powerpoint");
		contentTypeMap.put(".ppm", "application/x-ppm");
		contentTypeMap.put(".pps", "application/vnd.ms-powerpoint");
		contentTypeMap.put(".ppt", "application/vnd.ms-powerpoint");
		//contentTypeMap.put(".ppt", "application/x-ppt");
		contentTypeMap.put(".pr", "application/x-pr");
		contentTypeMap.put(".prf", "application/pics-rules");
		contentTypeMap.put(".prn", "application/x-prn");
		contentTypeMap.put(".prt", "application/x-prt");
		//contentTypeMap.put(".ps", "application/x-ps");
		contentTypeMap.put(".ps", "application/postscript");
		contentTypeMap.put(".ptn", "application/x-ptn");
		contentTypeMap.put(".pwz", "application/vnd.ms-powerpoint");
		contentTypeMap.put(".r3t", "text/vnd.rn-realtext3d");
		contentTypeMap.put(".ra", "audio/vnd.rn-realaudio");
		contentTypeMap.put(".ram", "audio/x-pn-realaudio");
		contentTypeMap.put(".ras", "application/x-ras");
		contentTypeMap.put(".rat", "application/rat-file");
		contentTypeMap.put(".rdf", "text/xml");
		contentTypeMap.put(".rec", "application/vnd.rn-recording");
		contentTypeMap.put(".red", "application/x-red");
		contentTypeMap.put(".rgb", "application/x-rgb");
		contentTypeMap.put(".rjs", "application/vnd.rn-realsystem-rjs");
		contentTypeMap.put(".rjt", "application/vnd.rn-realsystem-rjt");
		contentTypeMap.put(".rlc", "application/x-rlc");
		contentTypeMap.put(".rle", "application/x-rle");
		contentTypeMap.put(".rm", "application/vnd.rn-realmedia");
		contentTypeMap.put(".rmf", "application/vnd.adobe.rmf");
		contentTypeMap.put(".rmi", "audio/mid");
		contentTypeMap.put(".rmj", "application/vnd.rn-realsystem-rmj");
		contentTypeMap.put(".rmm", "audio/x-pn-realaudio");
		contentTypeMap.put(".rmp", "application/vnd.rn-rn_music_package");
		contentTypeMap.put(".rms", "application/vnd.rn-realmedia-secure");
		contentTypeMap.put(".rmvb", "application/vnd.rn-realmedia-vbr");
		contentTypeMap.put(".rmx", "application/vnd.rn-realsystem-rmx");
		contentTypeMap.put(".rnx", "application/vnd.rn-realplayer");
		contentTypeMap.put(".rp", "image/vnd.rn-realpix");
		contentTypeMap.put(".rpm", "audio/x-pn-realaudio-plugin");
		contentTypeMap.put(".rsml", "application/vnd.rn-rsml");
		contentTypeMap.put(".rt", "text/vnd.rn-realtext");
		contentTypeMap.put(".rtf", "application/msword");
		contentTypeMap.put(".rtf", "application/x-rtf");
		contentTypeMap.put(".rv", "video/vnd.rn-realvideo");
		contentTypeMap.put(".sam", "application/x-sam");
		contentTypeMap.put(".sat", "application/x-sat");
		contentTypeMap.put(".sdp", "application/sdp");
		contentTypeMap.put(".sdw", "application/x-sdw");
		contentTypeMap.put(".sit", "application/x-stuffit");
		contentTypeMap.put(".slb", "application/x-slb");
		contentTypeMap.put(".sld", "application/x-sld");
		contentTypeMap.put(".slk", "drawing/x-slk");
		contentTypeMap.put(".smi", "application/smil");
		contentTypeMap.put(".smil", "application/smil");
		contentTypeMap.put(".smk", "application/x-smk");
		contentTypeMap.put(".snd", "audio/basic");
		contentTypeMap.put(".sol", "text/plain");
		contentTypeMap.put(".sor", "text/plain");
		contentTypeMap.put(".spc", "application/x-pkcs7-certificates");
		contentTypeMap.put(".spl", "application/futuresplash");
		contentTypeMap.put(".spp", "text/xml");
		contentTypeMap.put(".ssm", "application/streamingmedia");
		contentTypeMap.put(".sst", "application/vnd.ms-pki.certstore");
		contentTypeMap.put(".stl", "application/vnd.ms-pki.stl");
		contentTypeMap.put(".stm", "text/html");
		contentTypeMap.put(".sty", "application/x-sty");
		contentTypeMap.put(".svg", "text/xml");
		contentTypeMap.put(".swf", "application/x-shockwave-flash");
		contentTypeMap.put(".tdf", "application/x-tdf");
		contentTypeMap.put(".tg4", "application/x-tg4");
		contentTypeMap.put(".tga", "application/x-tga");
		contentTypeMap.put(".tif", "image/tiff");
		//contentTypeMap.put(".tif", "application/x-tif");
		contentTypeMap.put(".tiff", "image/tiff");
		contentTypeMap.put(".tld", "text/xml");
		contentTypeMap.put(".top", "drawing/x-top");
		contentTypeMap.put(".torrent", "application/x-bittorrent");
		contentTypeMap.put(".tsd", "text/xml");
		contentTypeMap.put(".txt", "text/plain");
		contentTypeMap.put(".uin", "application/x-icq");
		contentTypeMap.put(".uls", "text/iuls");
		contentTypeMap.put(".vcf", "text/x-vcard");
		contentTypeMap.put(".vda", "application/x-vda");
		contentTypeMap.put(".vdx", "application/vnd.visio");
		contentTypeMap.put(".vml", "text/xml");
		contentTypeMap.put(".vpg", "application/x-vpeg005");
		contentTypeMap.put(".vsd", "application/vnd.visio");
		//contentTypeMap.put(".vsd", "application/x-vsd");
		contentTypeMap.put(".vss", "application/vnd.visio");
		contentTypeMap.put(".vst", "application/vnd.visio");
		//contentTypeMap.put(".vst", "application/x-vst");
		contentTypeMap.put(".vsw", "application/vnd.visio");
		contentTypeMap.put(".vsx", "application/vnd.visio");
		contentTypeMap.put(".vtx", "application/vnd.visio");
		contentTypeMap.put(".vxml", "text/xml");
		contentTypeMap.put(".wav", "audio/wav");
		contentTypeMap.put(".wax", "audio/x-ms-wax");
		contentTypeMap.put(".wb1", "application/x-wb1");
		contentTypeMap.put(".wb2", "application/x-wb2");
		contentTypeMap.put(".wb3", "application/x-wb3");
		contentTypeMap.put(".wbmp", "image/vnd.wap.wbmp");
		contentTypeMap.put(".wiz", "application/msword");
		contentTypeMap.put(".wk3", "application/x-wk3");
		contentTypeMap.put(".wk4", "application/x-wk4");
		contentTypeMap.put(".wkq", "application/x-wkq");
		contentTypeMap.put(".wks", "application/x-wks");
		contentTypeMap.put(".wm", "video/x-ms-wm");
		contentTypeMap.put(".wma", "audio/x-ms-wma");
		contentTypeMap.put(".wmd", "application/x-ms-wmd");
		contentTypeMap.put(".wmf", "application/x-wmf");
		contentTypeMap.put(".wml", "text/vnd.wap.wml");
		contentTypeMap.put(".wmv", "video/x-ms-wmv");
		contentTypeMap.put(".wmx", "video/x-ms-wmx");
		contentTypeMap.put(".wmz", "application/x-ms-wmz");
		contentTypeMap.put(".wp6", "application/x-wp6");
		contentTypeMap.put(".wpd", "application/x-wpd");
		contentTypeMap.put(".wpg", "application/x-wpg");
		contentTypeMap.put(".wpl", "application/vnd.ms-wpl");
		contentTypeMap.put(".wq1", "application/x-wq1");
		contentTypeMap.put(".wr1", "application/x-wr1");
		contentTypeMap.put(".wri", "application/x-wri");
		contentTypeMap.put(".wrk", "application/x-wrk");
		contentTypeMap.put(".ws", "application/x-ws");
		contentTypeMap.put(".ws2", "application/x-ws");
		contentTypeMap.put(".wsc", "text/scriptlet");
		contentTypeMap.put(".wsdl", "text/xml");
		contentTypeMap.put(".wvx", "video/x-ms-wvx");
		contentTypeMap.put(".xdp", "application/vnd.adobe.xdp");
		contentTypeMap.put(".xdr", "text/xml");
		contentTypeMap.put(".xfd", "application/vnd.adobe.xfd");
		contentTypeMap.put(".xfdf", "application/vnd.adobe.xfdf");
		contentTypeMap.put(".xhtml", "text/html");
		contentTypeMap.put(".xls", "application/vnd.ms-excel");
		//contentTypeMap.put(".xls", "application/x-xls");
		contentTypeMap.put(".xlw", "application/x-xlw");
		contentTypeMap.put(".xml", "text/xml");
		contentTypeMap.put(".xpl", "audio/scpls");
		contentTypeMap.put(".xq", "text/xml");
		contentTypeMap.put(".xql", "text/xml");
		contentTypeMap.put(".xquery", "text/xml");
		contentTypeMap.put(".xsd", "text/xml");
		contentTypeMap.put(".xsl", "text/xml");
		contentTypeMap.put(".xslt", "text/xml");
		contentTypeMap.put(".xwd", "application/x-xwd");
		contentTypeMap.put(".x_b", "application/x-x_b");
		contentTypeMap.put(".x_t", "application/x-x_t");	
	}
	
	private static String getContentType(String fileName) {
		String postfix = ".*";
		int index = fileName.lastIndexOf(".");
		if(index>=0){
			postfix = fileName.substring(index, fileName.length()).toLowerCase();
		}
		String contentType = contentTypeMap.get(postfix);
		if(contentType!=null){
			return contentType;
		}else{
			return "application/octet-stream";
		}
	}
	
	/**
	 * 
	 * #func 直接上传文件内容，不使用file<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public Boolean putObject(String content, String destPath) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		ByteArrayInputStream bais = null;
		try {
			//String fileName = file.getName();
			String fileName = destPath;
			String url = domain + bucket + "/" + fileName + "?";
			String resource = "/" + bucket + "/" + fileName;
			String contentType = getContentType(destPath);
			System.out.println("upload file: "+destPath +" , contentType"+contentType);
			
			bais = new ByteArrayInputStream(content.getBytes("utf-8"));
			long contentLength = bais.available();
			long expires = System.currentTimeMillis() / 1000 + 6000;
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPut = new HttpPut();
			httpPut.addHeader("content-type", contentType);
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("content-type", contentType);
			for(Map.Entry<String, String> entry : headers.entrySet()){
				httpPut.addHeader(entry.getKey(), entry.getValue());
			}
			String[] signArray = sign("PUT", expires, resource, headers, null);
			
			String authUrl = url + "ssig="
					+ URLEncoder.encode(signArray[0], "utf-8") + "&Expires=" + expires
					+ "&KID=" + signArray[1];
			System.out.println(authUrl);
			
			httpPut.setURI(URI.create(authUrl));
			
			// 发送请求
			InputStreamEntity ise = new InputStreamEntity(bais, contentLength);
			httpPut.setEntity(ise);
			HttpResponse httpResponse = httpClient.execute(httpPut);
//			System.out.println(httpResponse.getStatusLine().getStatusCode());
//			System.out.println("response headers:");
//			for(Header header :httpResponse.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			
			//刷新cdn
			try{
				Boolean flag = SinaEdgeCdnClient.purgeSinaEdge(new String[]{Variables.S3_IDEA_PREFIX_URL+"/"+destPath});
				System.out.println("put object to s3, reflush cdn :"+flag);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return httpResponse.getStatusLine().getStatusCode() == HTTP_200_OK;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(in);
			close(out);
			close(bais);
		}
	}
	
	public Boolean putObject(File file, String destPath) {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		FileInputStream fis = null;
		try {
			//String fileName = file.getName();
			String fileName = destPath;
			String url = domain + bucket + "/" + fileName + "?";
			String resource = "/" + bucket + "/" + fileName;
			String contentType = getContentType(destPath);
			System.out.println("upload file: "+destPath +" , contentType"+contentType);
			
			fis = new FileInputStream(file);
			long contentLength = fis.available();
			long expires = System.currentTimeMillis() / 1000 + 6000;
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPut = new HttpPut();
			httpPut.addHeader("content-type", contentType);
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("content-type", contentType);
			for(Map.Entry<String, String> entry : headers.entrySet()){
				httpPut.addHeader(entry.getKey(), entry.getValue());
			}
			String[] signArray = sign("PUT", expires, resource, headers, null);
			
			String authUrl = url + "ssig="
					+ URLEncoder.encode(signArray[0], "utf-8") + "&Expires=" + expires
					+ "&KID=" + signArray[1];
			System.out.println(authUrl);
			
			httpPut.setURI(URI.create(authUrl));
			
			// 发送请求
			InputStreamEntity ise = new InputStreamEntity(fis, contentLength);
			httpPut.setEntity(ise);
			HttpResponse httpResponse = httpClient.execute(httpPut);
//			System.out.println(httpResponse.getStatusLine().getStatusCode());
//			System.out.println("response headers:");
//			for(Header header :httpResponse.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			
			//刷新cdn
			try{
				Boolean flag = SinaEdgeCdnClient.purgeSinaEdge(new String[]{Variables.S3_IDEA_PREFIX_URL+"/"+destPath});
				System.out.println("put object to s3, reflush cdn :"+flag);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return httpResponse.getStatusLine().getStatusCode() == HTTP_200_OK;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(in);
			close(out);
			close(fis);
		}
	}
	
	public Boolean copyObject(String destName, String srcName) {
		try {
			String url = domain + bucket + "/" + destName +"?copy";
			String resource = "/" + bucket + "/" + destName +"?copy";
			long expires = System.currentTimeMillis() / 1000 + 6000;
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPut httpPut = new HttpPut();
			Map<String,String> headers = new HashMap<String,String>();
			headers.put("x-amz-copy-source", "/"+bucket+"/"+srcName);
			for(Map.Entry<String, String> entry : headers.entrySet()){
				httpPut.addHeader(entry.getKey(), entry.getValue());
			}
			
			String[] signArray = sign("PUT", expires, resource, headers, null);

			String authUrl = url + "&ssig="
					+ URLEncoder.encode(signArray[0], "utf-8") + "&Expires=" + expires
					+ "&KID=" + signArray[1];
			//System.out.println(authUrl);
			
			httpPut.setURI(URI.create(authUrl));
			
//			for(Header header :httpPut.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(httpPut);
//			System.out.println("response headers:");
//			for(Header header :httpResponse.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			return httpResponse.getStatusLine().getStatusCode() == HTTP_200_OK;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
		}
	}
	
	public Boolean deleteObject(String fileName) {
		try {
			String url = domain + bucket + "/" + fileName +"?";
			String resource = "/" + bucket + "/" + fileName;
			long expires = System.currentTimeMillis() / 1000 + 6000;
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpDelete httpDelete = new HttpDelete();
			Map<String,String> headers = new HashMap<String,String>();
			for(Map.Entry<String, String> entry : headers.entrySet()){
				httpDelete.addHeader(entry.getKey(), entry.getValue());
			}
			
			String[] signArray = sign("DELETE", expires, resource, headers, null);
			
			
			String authUrl = url + "ssig="
					+ URLEncoder.encode(signArray[0], "utf-8") + "&Expires=" + expires
					+ "&KID=" + signArray[1];
			//System.out.println(authUrl);
			
			httpDelete.setURI(URI.create(authUrl));
			
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(httpDelete);
//			System.out.println("response headers:");
//			for(Header header :httpResponse.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			
			//刷新cdn
			try{
				Boolean flag = SinaEdgeCdnClient.purgeSinaEdge(new String[]{Variables.S3_IDEA_PREFIX_URL+"/"+fileName});
				System.out.println("delete object from s3, reflush cdn :"+flag);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return httpResponse.getStatusLine().getStatusCode() == HTTP_204_NO_CONTENT;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
		}
	}
	
	public String getFileList() {
		try {
			String url = domain + bucket + "/" + "?formatter=json";
			String resource = "/" + bucket + "/" ;//由于formatter不在extraArray里，就不在需要编码的范围内，故不能加入到resource中
			long expires = System.currentTimeMillis() / 1000 + 6000;
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet();
			Map<String,String> headers = new HashMap<String,String>();
			for(Map.Entry<String, String> entry : headers.entrySet()){
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			
			String[] signArray = sign("GET", expires, resource, headers, null);
			
			
			String authUrl = url + "&ssig="
					+ URLEncoder.encode(signArray[0], "utf-8") + "&Expires=" + expires
					+ "&KID=" + signArray[1];
			//System.out.println(authUrl);
			
			httpGet.setURI(URI.create(authUrl));
			
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(httpGet);
//			System.out.println("response headers:");
//			for(Header header :httpResponse.getAllHeaders()){
//				System.out.println(header.getName()+":"+header.getValue());
//			}
			HttpEntity httpEntity = httpResponse.getEntity();
			//System.out.println(httpResponse.getStatusLine().getStatusCode());
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK && httpEntity != null) {
                String html = EntityUtils.toString(httpEntity, "GBK");
                EntityUtils.consume(httpEntity);
                return html;
            }
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}

	private static void close(Closeable c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

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
	/**
	 * 计算签名
	 * 
	 * @param httpVerb
	 * @param contentMD5
	 * @param contentType
	 * @param date
	 * @param resource
	 * @param metas
	 * @return
	 */
	private static String[] sign(String httpVerb, long expire,
			String resource, Map<String, String> headers, Map<String, String> metas) {
		try {
			//要保证resource的？后面，是指定的一些值，这里就先不做验证，由上面调用函数保证
			
			Map<String, String> arrayToSign = new HashMap<String, String>();
			arrayToSign.put("HTTP-Verb", httpVerb);
			arrayToSign.put("Content-MD5", "");
			arrayToSign.put("Content-Type", "");
			arrayToSign.put("Date", "");
			arrayToSign.put("CanonicalizedAmzHeaders", "");
			arrayToSign.put("CanonicalizedResource", resource);
			
			if(headers.get("s-sina-sha1")!=null) {
				arrayToSign.put("Content-MD5", headers.get("s-sina-sha1"));
			} else if (headers.get("s-sina-md5")!=null) {
				arrayToSign.put("Content-MD5", headers.get("s-sina-md5"));
			} else if (headers.get("content-md5")!=null) {
				arrayToSign.put("Content-MD5", headers.get("content-md5"));
			}
			
			if (headers.get("content-type")!=null) {
				arrayToSign.put("Content-Type", headers.get("content-type"));
			}
			
			if (headers.get("date")!=null) {
				arrayToSign.put("Date", headers.get("date"));
			} else if (expire > 0) {
				arrayToSign.put("Date", String.valueOf(expire));
			}
			
			StringBuffer sb = new StringBuffer();
			for(Map.Entry<String, String> entry : headers.entrySet()){
				String key = entry.getKey();
				if(key.startsWith("x-amz-") || key.startsWith("x-sina-")){
					sb.append(key+":"+entry.getValue()+"\n");
				}
			}
			if(sb.length()>0){
				arrayToSign.put("CanonicalizedAmzHeaders", sb.toString());
			}
			
			String stringToSign = checkNull(arrayToSign.get("HTTP-Verb")) + "\n"
			                    + checkNull(arrayToSign.get("Content-MD5")) + "\n"
			                    + checkNull(arrayToSign.get("Content-Type")) + "\n"
			                    + checkNull(arrayToSign.get("Date")) + "\n"
			                    + checkNull(arrayToSign.get("CanonicalizedAmzHeaders"))
			                    + checkNull(arrayToSign.get("CanonicalizedResource"));
			
//			System.out.println("StringToSign\n" + stringToSign);
//			System.out.println("secretKey:" + secretKey);
			String signature = hmac_sha1(stringToSign, secretKey);
//			System.out.println("signature:" + signature);
			String encodedBase64 = new String(Base64.encodeBase64(signature
					.getBytes("ISO-8859-1")));
//			System.out.println("encodedBase64:" + encodedBase64);
			signature = encodedBase64.substring(5, 15);
//			System.out.println("signature 5-15:" + signature);
			
			String accessKeyLower = accessKey.toLowerCase();
			String tmp1 = accessKeyLower.split("0")[0];
			String tmp2 = accessKeyLower
					.substring(accessKeyLower.length() - 10);
			String kid = tmp1 + "," + tmp2;
			
			return new String[]{signature, kid};
		} catch (Exception e) {
			throw new RuntimeException("MAC CALC FAILED.");
		}
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
	

	public static void main(String[] args) throws Exception {
		//File file = new File("D:/69c732e4gw1dtvpvet3wag.gif");
		File file = new File("D:/2.flv");
		//File file = new File("D:/null.jpg");
		S3Client s3Client = S3Client.getInstanse();
		//System.out.println(s3Client.putObject("<html><body>hello test</body></html>", "sax/test/test.html"));
		//System.out.println(s3Client.putObject(file, "sax/test/2.flv"));
		//System.out.println(s3Client.deleteObject("sax/test/69c732e4gw1dtvpvet3wag.gif"));
		System.out.println(s3Client.deleteObject("sax/advertiser/1/0/150/qualification0/03e8eadeefdf4fed7446a69a3909b847.gif"));
		//System.out.println(s3Client.copyObject("sax/test/2.gif", "sax/test/1.gif"));
		//System.out.println(s3Client.getFileList());
		
		//String[] urls = new String[]{"http://img.amp.ad.sina.com.cn/sax/offline/1/0/7/1.jpg"};
		//String[] urls = new String[]{"http://img.amp.ad.sina.com.cn/sax/cputest/15.swf",
		//		"http://img.amp.ad.sina.com.cn/sax/cputest/14.swf"};
		//boolean content = SinaEdgeCdnClient.purgeSinaEdge(urls);
		//String content = HttpTools.getTextByHttp("http://connect.sinaedge.com/object/purge");
		//System.out.println(content);
	}
}
