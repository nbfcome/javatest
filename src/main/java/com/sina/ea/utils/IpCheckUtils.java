package com.sina.ea.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sina.adm.base.utils.IPUtils;

@Service
public class IpCheckUtils {

	protected static Logger log = LoggerFactory.getLogger(IpCheckUtils.class);
	
	/**
	 * 
	 * #func ip验证<br>
	 * #desc ip以“.”分隔，每个分隔可以为数字，或者“*”，不能为其他形式。
	 *       如果是“*”，则该段就通过，继续匹配下段。如果是非“*”，则进行字符串匹配
	 *       全部匹配成功则表示ip通过验证
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	private static boolean isIpMatch(String ipPattern, String ip){
		if(StringUtils.isBlank(ipPattern)||StringUtils.isBlank(ip)){
			return false;
		}
		String[] ipPatternArray = ipPattern.split("\\.");
		String[] ipArray = ip.split("\\.");
		if(ipPatternArray == null || ipPatternArray.length!=4
				|| ipArray == null || ipArray.length !=4){
			return false;
		}
		for(int i=0;i<4;i++){
			if("*".equals(ipPatternArray[i])){
				continue;
			}
			if(StringUtils.isBlank(ipPatternArray[i])||StringUtils.isBlank(ipArray[i])){
				return false;
			}
			if(!ipPatternArray[i].trim().equals(ipArray[i].trim())){
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * #func 验证ip是否在允许集合内<br>
	 * #desc 
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static Boolean checkIpAllow(HttpServletRequest request, String ipList){		
		if(request == null || StringUtils.isBlank(ipList)){
			return false;
		}
		//ip限制
		String accessIp = IPUtils.getIpAddr(request);
		log.info("connect IP : "+accessIp);
		String allowIps = ipList;
		String[] arrayIps = allowIps.split(";");
		
		boolean isAllow = false;
		for(String allowIp : arrayIps){
			if(isIpMatch(allowIp, accessIp)){
				isAllow = true;
				break;
			}
		}
		return isAllow;
	}
}