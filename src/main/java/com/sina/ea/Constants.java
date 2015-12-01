package com.sina.ea;

/**
 * 定义整个工程用的常量
 * 
 * @author zhangzhibo
 * @version 1.0.0
 * 
 */
public class Constants {
	
	/**
	 * 变量文件名
	 */
	public static String PROPERTIES_FILE_NAME = "variables_core.properties";
	
	/**
	 * 分页默认大小
	 */
	public static int PAGE_SIZE = 40;
	
	/**
	 * 
	 * #func 系统常量0、1
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static Long DEFAULT_NO = 0L;
	public static Long DEFAULT_YES = 1L;
	
	/**
	 *  效果广告平台在审核系统中的系统id
	 */
	public static Long IDEA_AUDIT_SYS_ID = 2L;
	
	public static double ADVERT_MIN_BID_PRICE_FOR_NORMAL = 0.2d;
	
	//唤起模板尺寸
	public static final String HQ_TEMPLATE_SIZE = "38";
	
	//资源类型名
	public static final String ADPOS_TYPE_NAME = "图文"; 
	
	/**
	 * 图文创意尺寸
	 */
	public static final String GRAPHIC_SIZE = "198*132";
	
	/**
	 * 移动联盟创意尺寸
	 */
	public static final String XXLUNION_SIZE = "160*120";
	
	//正则匹配有且只有ios操作系统
	public static final String REGEX_STRING = ".*5:1022(\\|[0-9]*:|\\s*)";

	
	/**
	 * 人群包人群接入方式
	 * @author biaofei
	 *
	 */
	public static class CROWDPACKAGE_INTERFACE{
		//api接口
		public static Long API_INTERFACE = 1L;
		//代码监测
		public static Long CODE_INTERFACE = 2L;
		
	}
	
	/**
	 * 创意送审
	 * @author biaofei
	 *
	 */
	public static class IDEA_AUDIT{
		//无线
		public static String PLATFORM = "无线";
		
	}
	
	/**
	 * 
	 * #func 字典表<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 *
	 */
	public static class DICNAME{
		//sax资源类型列表
		public static String SAX_ADPOS_TYPE = "sax_adpos_type";
		//sax资源所属频道列表
		public static String SAX_ADPOS_CHANNEL = "sax_adpos_channel";
		//sax资源所属平台列表
		public static String SAX_ADPOS_PLATFORM = "sax_adpos_platform";
	}
	
	/**
	 * 
	 * #func 用户类型
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class CUSTOMER_TYPE {
		//中小用户
		public static Long SMALL = 0L;
		//KA大客户
		public static Long KA = 1L;
		//代理商
		public static Long AGENT = 2L;
		//super KA大客户
		public static Long SUPER_KA = 3L;
	}
	
	/**
	 * 广告位的类型
	 * 
	 * @author biaofei
	 * 
	 */
	public static class ADPOS_TYPE {
		//信息流
		public static String INFOFLOW = "xxl";
		//信息流app
		public static String INFOFLOWAPP = "xxlapp";
		//信息流wap
		public static String INFOFLOWWAP = "xxlwap";
		//推荐图文
		public static String TUIGRAPHIC = "tuigraph";
		//移动联盟信息流
		public static String UNIONINFOFLOW = "1009";
		//文字链
		public static String WZL = "wzl";
	}
	
	/**
	 * 
	 * #func 广告位的资源权限
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class ADPOS_RIGHT_TYPE {
		//中小资源
		public static String SMALL = "01000";
		//KA资源
		public static String KA = "11000";
		//super-KA资源
		public static String SUPER_KA = "10000";
	}
	
	/**
	 * 
	 * #func 角色类型
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class ROLE_TYPE {
		//普通用户
		public static Long NORMAL = 1L;
		//代理
		public static Long AGENT = 2L;
		//第三方
		public static Long THIRD = 3L;
	}
	
	/**
	 * 
	 * #func 普通状态，可通用广告组、广告、创意
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class STATUS {
		//正常
		public static Long NORMAL = 0L;
		//暂停
		public static Long PAUSE = 1L;
		//删除
		public static Long DELETE = 2L;
	}
	
	/**
	 * 
	 * #func 审核状态，可通用
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class AUDIT_STATUS {
		//未处理
		public static Long READY = 0L;
		//同意
		public static Long ACCEPT = 1L;
		//拒绝
		public static Long REFUSE = 2L;
		//临时状态
		public static Long TEMP = 3L;
	}
	
	/**
	 * 
	 * 投放引擎的状态
	 * @author zhouwei
	 * @version 1.0
	 * 
	 */
	public static class ENGINE_STATUS {
		//有效
		public static Long ACTIVE = 1L;
		//无效
		public static Long INACTIVE = 0L;
	}
	
	/**
	 * 
	 * #func 创意类型
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 * 
	 */
	public static class IDEA_TYPE {
		//图片
		public static Long PICTURE = 0L;
		//flash
		public static Long FLASH = 1L;
		//文字链
		public static Long TEXT = 2L;
		//adbox创意
		public static Long ADBOX = 3L;
		//信息流
		public static Long INFOFLOW = 4L;
		//图文
		public static Long GRAPHIC = 5L;
		//KA信息流
		public static Long KAINFOFLOW=6L;
		//移动联盟创意
		public static Long UNIONINFOFLOW = 7L;
		//动态
		public static Long DYNAMIC = 8L;

	}
	
	/**
	 * 竞价类型
	 */
	public static class BID_TYPE {
		//cpm
		public static Long CPM = 0L;
		//cpc
		public static Long CPC = 1L;
	}
	
	/**
	 * 广告类型
	 */
	public static class ADVERT_TYPE {
		//普通
		public static Long NORMAL = 0L;
		//文字链
		public static Long WORDCHAIN = 1L;
		//信息流
		public static Long INFOFLOW = 2L;
		//移动联盟信息流
		public static Long UNIONINFOFLOW = 3L;
	}
	
	/**
	 * 前端展示的广告类型
	 * 
	 * @author biaofei
	 *
	 */
	public static class FRONT_ADVERT_TYPE{
		//图片
		public static String IMAGE = "image";
		//
		public static String FLASH = "flash";
		//
		public static String TEXT = "text";
		//
		public static String ADBOX = "adbox";
		//
		public static String XXL = "xxl";
		//
		public static String HTML = "html";
	}
	
	/**
	 * 广告组投放平台类型
	 */
	public static class ADVERTGROUP_PLATFORM {
		//PC
		public static String PC = "PC";
		//WAP
		public static String WAP = "WAP";
		//移动联盟
		public static String MOB_UNION = "MOB_UNION";
	}
	
	/**
	 * 定向类型
	 */
	public static class ORIENTATION_TYPE{
		//用户兴趣定向
		public static String USERTAG = "user_tag";
		public static Long USERTAG_ID = 1L;
		//用户地域定向
		public static String USERZONE = "v_zone";
		public static Long USERZONE_ID = 2L;
		//用户男女定向
		public static String USERGENDER = "user_gender";
		public static Long USERGENDER_ID = 3L;
		//用户年龄定向
		public static String USERAGE = "user_age";
		public static Long USERAGE_ID = 4L;
		//手机操作系统定向
		public static String WAPSYSTEM = "wap_os";
		public static Long WAPSYSTEM_ID = 5L;
		//内容定向
		public static String PAGECONTENT = "page_content";
		public static Long PAGECONTENT_ID = 6L;
		//移动联盟兴趣定向
		public static String MOB_USER_TAG = "mobile_tag";
		public static Long MOB_USER_TAG_ID = 7L;
		//移动网络定向
		public static String MOB_NET = "net_type";
		public static Long MOB_NET_ID = 8L;
		//移动联盟lbs定向
		public static String MOB_LBS = "v_lbs";
		public static Long MOB_LBS_ID = 9L;
		//移动app类别定向
		public static String MOB_APP_CATEGORY = "app_type";
		public static Long MOB_APP_CATEGORY_ID = 10L;

	}
	
	/**
	 * 定向的常量值
	 */
	public static class ORIENTATION_CONSTANT {
		//ORIENTATION
		public static String ORIENTATION = "orientation";
		//PARENT_ID
		public static String PARENT_ID = "parentId";
		//CLIENT
		public static String CHILDREN = "children";
	}
	/**
	 * 截图状态
	 * @author qiaozhenpeng
	 *
	 */
	public static class SCREEN_STATE{
		//未截图
		public static Long NO_SCREEN=0l;
		//已截图
		public static Long YES_SCREEN=1l;
		//截图中
		public static Long UNDERWAY_SCREEN=2l;
	}
	
	/**
	 * 产品线
	 * @author WSH
	 *
	 */
//	public static class SERVICE_LINE_CODE{
//		//扶翼
//		public static String PINPAI_CPC="PINPAI-CPC";
//		//KA信息流
//		public static String SHANGXUN_CPC="SHANGXUN-CPC";
//	}
}
