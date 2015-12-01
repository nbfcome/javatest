package com.sina.ea;

import com.sina.adm.base.utils.PropertyUtil;

public class Variables {
	private static PropertyUtil propUtil = null;

	static {
		propUtil = new PropertyUtil(Constants.PROPERTIES_FILE_NAME);
	}
	
	/**
	 * sso通过php接口返回cookie解析结果的php服务url
	 */
	public static String SSO_PHP_GET_COOKIE_URL = propUtil.getProperty("SSO_PHP_GET_COOKIE_URL");
	
	/**
	 * sso通接口获取用户uuid的url
	 */
	public static String SSO_GET_UNIQUE_ID_URL = propUtil.getProperty("SSO_GET_UNIQUE_ID_URL");
	
	/**
	 * sso的entry
	 */
	public static String SSO_ENTRY = propUtil.getProperty("SSO_ENTRY");
	
	/**
	 * sso的pin码
	 */
	public static String SSO_PIN = propUtil.getProperty("SSO_PIN");
	
	/**
	 * 允许sax的ip限制，ip列表，逗号分隔
	 */
	public static String ALLOW_IPS_FOR_SAX = propUtil.getProperty("ALLOW_IPS_FOR_SAX");
	
	/**
	 * 允许门户的ip限制，ip列表，逗号分隔
	 */
	public static String ALLOW_IPS_FOR_PORTAL = propUtil.getProperty("ALLOW_IPS_FOR_PORTAL");

	/**
	 * ASP接口调用url
	 */
	public static String ASP_INTERFACE_URL = propUtil.getProperty("ASP_INTERFACE_URL");
	
	/**
	 * 创意审核平台提交创意url
	 */
	public static String IDEA_AUDIT_UPLOAD_URL = propUtil.getProperty("IDEA_AUDIT_UPLOAD_URL");
	
	
	public static String SHOW_SAVE_SAX_CACHE_URL = propUtil.getProperty("SHOW_SAVE_SAX_CACHE_URL");
	
	
	/**
	 * 创意审核平台状态查询url
	 */
	public static String IDEA_AUDIT_STATUS_QUERY_URL = propUtil.getProperty("IDEA_AUDIT_STATUS_QUERY_URL");

	/**
	 * s3物料访问的前缀url
	 */
	public static String S3_IDEA_PREFIX_URL = propUtil.getProperty("S3_IDEA_PREFIX_URL");
	
	/**
	 * 商业门户绑定用户接口
	 */
	public static String PORTAL_SET_BIND_CLIENT_URL = propUtil.getProperty("PORTAL_SET_BIND_CLIENT_URL");
	
	/**
	 * 商业门户验证是否代理商可以代理用户进行广告系统接口
	 */
	public static String PORTAL_CHECK_ENTER_ADVERT_URL = propUtil.getProperty("PORTAL_CHECK_ENTER_ADVERT_URL");
	
	/**
	 * 商业门户查询用户具体信息接口
	 */
	public static String PORTAL_GET_USER_INFO_URL = propUtil.getProperty("PORTAL_GET_USER_INFO_URL");
	
	/**
	 * 商业门户查询代理商信息接口
	 */
	public static String PORTAL_GET_AGENT_INFO_URL = propUtil.getProperty("PORTAL_GET_AGENT_INFO_URL");
	
	/**
	 * 商业门户查询用户实际名称接口
	 */
	public static String PORTAL_GET_QUALIFICATION_INFO_URL = propUtil.getProperty("PORTAL_GET_QUALIFICATION_INFO_URL");
	
	/**
	 * ADBOX创意查询接口
	 */
	public static String ADBOX_GET_INFO_API_URL = propUtil.getProperty("ADBOX_GET_INFO_API_URL");
	
	/**
	 * ADBOX获取用户所有创意模板接口
	 */
	public static String ADBOX_GET_LIST_API_URL = propUtil.getProperty("ADBOX_GET_LIST_API_URL");
	
	/**
	 * 用户注册需要访问的adbox url，以便adbox也生成该用户信息
	 */
	public static String ADBOX_REGISTER_URL = propUtil.getProperty("ADBOX_REGISTER_URL");
	
	/**
	 *   创意上传图片允许类型后缀
	 */
	public static String IDEA_PICTURE_SUFFIX_ALLOW_LIST = propUtil.getProperty("IDEA_PICTURE_SUFFIX_ALLOW_LIST");
	
	/**
	 *   信息流创意上传图片允许类型后缀
	 */
	public static String IDEA_INFOFLOW_SUFFIX_ALLOW_LIST = "jpg;jpeg;png";
	
	/**
	 *   移动联盟信息流创意上传图片允许类型后缀
	 */
	public static String IDEA_UNIONINFOFLOW_SUFFIX_ALLOW_LIST = "jpg";
	
	/**
	 *   wap平台创意上传图片允许类型后缀
	 */
	public static String IDEA_WAP_SUFFIX_ALLOW_LIST = "jpg;jpeg;png";
	
	/**
	 *   创意上传flash允许类型后缀
	 */
	public static String IDEA_FLASH_SUFFIX_ALLOW_LIST = propUtil.getProperty("IDEA_FLASH_SUFFIX_ALLOW_LIST");
	
	/**
	 *   创意上传图片允许最大大小
	 */
	public static Long IDEA_PICTURE_MAX_SIZE = Long.valueOf(propUtil.getProperty("IDEA_PICTURE_MAX_SIZE"));
	
	/**
	 * 动态创意logo允许最大大小10k
	 */
	public static final Long IDEA_DYNAMIC_PIC_MAX_SIZE = 10240l;
	/**
	 *   信息流广告创意上传图片允许最大大小
	 */
	public static Long IDEA_INFOFLOW_PICTURE_MAX_SIZE = Long.valueOf(propUtil.getProperty("IDEA_INFOFLOW_PICTURE_MAX_SIZE"));
	
	/**
	 *   创意上传FLASH允许最大大小
	 */
	public static Long IDEA_FLASH_MAX_SIZE = Long.valueOf(propUtil.getProperty("IDEA_FLASH_MAX_SIZE"));
	
	/**
	 * 创意审核，flash版本最大数值
	 */
	public static Long IDEA_AUDIT_MAX_FLASH_VERSION = Long.valueOf(propUtil.getProperty("IDEA_AUDIT_MAX_FLASH_VERSION"));
	
	/**
	 *   临时下载文件目录
	 */
	public static String DOWNLOAD_FILE_TEMP_DIR = propUtil.getProperty("DOWNLOAD_FILE_TEMP_DIR");
	
	/**
	 * 商业门户分配给效果平台的类别字符串
	 */
	public static String PORTAL_EA_TYPE_STR = propUtil.getProperty("PORTAL_EA_TYPE_STR");

	/**
	 * 商业门户分配给KA信息流平台的产品线字符串
	 * add by 2014-11-25
	 * @author songjuan
	 */
	public static String PORTAL_KA_TYPE_STR = propUtil.getProperty("PORTAL_KA_TYPE_STR");

	
	/**
	 * 数据中心获取基础数据ip限制
	 */
	public static String ALLOW_IPS_FOR_DATA_CENTER = propUtil.getProperty("ALLOW_IPS_FOR_DATA_CENTER");
	
	/**
	 * 商业门户开通账户接口
	 */
	public static String ALLOW_IPS_FOR_PFP = propUtil.getProperty("ALLOW_IPS_FOR_PFP");
	
	/**
	 * 效果广告作为dsp的id
	 */
	public static String EA_DSP_ID = propUtil.getProperty("EA_DSP_ID");
	
	/**
	 * 效果广告的pc平台竞价最低金额
	 */
	public static Double ADVERT_MIN_BID_PRICE_FOR_PC = Double.valueOf(propUtil.getProperty("ADVERT_MIN_BID_PRICE_FOR_PC"));
	
	/**
	 * 效果广告的wap平台竞价最低金额
	 */
	public static Double ADVERT_MIN_BID_PRICE_FOR_WAP = Double.valueOf(propUtil.getProperty("ADVERT_MIN_BID_PRICE_FOR_WAP"));
	
	/**
	 * 效果广告的信息流竞价最低金额
	 */
	public static Double ADVERT_MIN_BID_PRICE_FOR_INFOFLOW = Double.valueOf(propUtil.getProperty("ADVERT_MIN_BID_PRICE_FOR_INFOFLOW"));
	
	/**
	 * 效果广告的无线联盟平台竞价最低金额
	 */
	public static Double ADVERT_MIN_BID_PRICE_FOR_MOB_UNION = Double.valueOf(propUtil.getProperty("ADVERT_MIN_BID_PRICE_FOR_MOB_UNION"));
	
	/**
	 * 翻牌的创意，线上物料地址必须是d3开头，因为有跨域问题，需要特殊处理
	 * 配置强制设定到某一个静态池地址上
	 */
	public static String FORCE_ID_FOR_FANPAI = propUtil.getProperty("FORCE_ID_FOR_FANPAI");
	
	/**
	 * 翻牌的创意，线上物料地址必须是d3开头，因为有跨域问题，需要特殊处理
	 * 配置翻牌尺寸，暂时不从资源管理查询
	 */
	public static String FANPAI_WDHT = propUtil.getProperty("FANPAI_WDHT");
	
	/**
	 * 文字链内容长度最小值
	 */
	public static Integer WENZILIAN_CONTENT_MIN_SIZE = Integer.valueOf(propUtil.getProperty("WENZILIAN_CONTENT_MIN_SIZE"));
	
	/**
	 * 文字链内容长度最大值
	 */
	public static Integer WENZILIAN_CONTENT_MAX_SIZE = Integer.valueOf(propUtil.getProperty("WENZILIAN_CONTENT_MAX_SIZE"));
	
	/**
	 * 截屏服务器列表
	 */
	public static final String SCREEN_CAPTURE_SERVERS = propUtil.getProperty("SCREEN_CAPTURE_SERVERS");
	/**
	 * 截屏文件所在目录（由log生成）
	 */
	public static final String SCREEN_CAPTURE_PATH = propUtil.getProperty("SCREEN_CAPTURE_PATH");
	/**
	 * 截屏服务回调的url
	 */
	public static final String SCREEN_CAPTURE_BACKURL = propUtil.getProperty("SCREEN_CAPTURE_BACKURL");
	/**
	 * 删除导入数据时间以小时为单位
	 */
	public static final Long SCREEN_DELETE_INFO=Long.valueOf(propUtil.getProperty("SCREEN_DELETE_INFO"));
	/**
	 * 删除截图数据时间 以小时为单位
	 */
	public static final Long SCREEN_DELETE_IDEA_SCREEN_INFO=Long.valueOf(propUtil.getProperty("SCREEN_DELETE_IDEA_SCREEN_INFO"));
	/**
	 * 创业最大截图数量 
	 */
	public static final Long SCREEN_MAX_SCREEN=Long.valueOf(propUtil.getProperty("SCREEN_IDEA_MAX_NUM"));
	
	/**
	 * 投放组每一段时间内最大暂停次数
	 */
	public static final Long ADVERT_GROUP_MAX_PAUSE_NUM = Long.valueOf(propUtil.getProperty("ADVERT_GROUP_MAX_PAUSE_NUM"));
	
	/**
	 * 投放组暂停次数计算的时间间隔，单位分钟
	 */
	public static final Long ADVERT_GROUP_MAX_PAUSE_TIME = Long.valueOf(propUtil.getProperty("ADVERT_GROUP_MAX_PAUSE_TIME"));
	/**
	 * 截图导入文件路径
	 */
	public static final String SCREEN_PATH=propUtil.getProperty("SCREEN_PATH");
	/**
	 * 截图导入文件路径
	 */
	public static final String INNER_FOLDER=propUtil.getProperty("INNER_FOLDER");
	/**
	 * 截图导入文件路径
	 */
	public static final String FILE_PREFIX=propUtil.getProperty("FILE_PREFIX");
	/**
	 * 查询为解图数量
	 */
	public static final Integer AUTO_SCREEN_PIC_NUM=Integer.valueOf(propUtil.getProperty("AUTO_SCREEN_PIC_NUM"));
	
	/**
	 * 内容定向频道算法获取url
	 */
	public static final String ADVERT_CONTENT_ORIENTATION_GET_URL = propUtil.getProperty("ADVERT_CONTENT_ORIENTATION_GET_URL");
	/**
	 * 截图数据临时存储位置
	 */
	public static final String SCREENOUTPUTPATH = propUtil.getProperty("SCREENOUTPUTPATH");
	
	/**
	 * 行业推荐包存储位置
	 */
	public static final String ORIENTATIONPATH = propUtil.getProperty("ORIENTATION_PATH");
	
	/**
	 * 由算法组预估数据：预估平均点击率、预估曝光量、平均出价及胜出率
	 */
	public static String ESTIMATEDATA = propUtil.getProperty("ESTIMATEDATA");
	
	/**
	 * 信息流创意预览key的保存时间
	 */
	public static final Long INFO_IDEA_SESSION_EXPIRE = Long.valueOf(propUtil.getProperty("INFO_IDEA_SESSION_EXPIRE")); 
}
