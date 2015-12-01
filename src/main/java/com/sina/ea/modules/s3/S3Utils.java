package com.sina.ea.modules.s3;


/**  
 *   
 * MemcachedUtils  
 * 
 * wujun2@staff.sina.com.cn 
 * 2012-4-23 下午2:01:33  
 *   
 */
public class S3Utils extends PropertieUtils {

	protected final static String FILENAME = "s3.properties";  
	/**  
	 * 创建一个新的实例 S3Utils.  
	 *  
	 */
	private S3Utils() {
		super(FILENAME);
	}
	
	public static String getSecretKey() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("secretKey");
	}
	public static String getAccessKey() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("accessKey");
	}
	public static String getBucket() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("bucket");
	}
	public static String getDomain() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("domain");
	}
	public static String getSinaEdgeKid() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("sinaEdgeKid");
	}
	public static String getSinaEdgeKey() {
		S3Utils messageUtils = new S3Utils();
		return messageUtils.getString("sinaEdgeKey");
	}
	public static void main(String[] args) {
		System.out.println(getSecretKey());
	}
	
}
