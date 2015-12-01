package com.sina.ea.modules.sso;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sina.adm.base.components.encoder.Md5Encoder;
import com.sina.ea.utils.EAHttp;
import com.sina.adm.base.utils.JSONUtils;
import com.sina.ea.Variables;

@Service
public class SsoTools {
	
	/**
	 * 
	 * #func 根据cookie，通过php解析出sso信息<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static SsoVo getSsoInfoFromCookie(HttpServletRequest request){
		
		String url = Variables.SSO_PHP_GET_COOKIE_URL;
		String urls[] = url.split(";");
		String json = EAHttp.getTextByHttpWithPostWayAndSendCookie(urls, null, request.getCookies());
		SsoVo ssoVo = JSONUtils.fromJson(json, SsoVo.class);
		return ssoVo;
	}
	
	/**
	 * 
	 * #func 根据用户登录id，获取用户的sso唯一id<br>
	 * #desc 在此添加实现相关说明
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static Long getSsoUniqueId(String loginId){
		if(StringUtils.isBlank(loginId)){
			return null;
		}
		String url = Variables.SSO_GET_UNIQUE_ID_URL;
		String urls[] = new String[]{url};
		
		String entry = Variables.SSO_ENTRY;
		String pin = Variables.SSO_PIN;
		String user = loginId;
		//调用者路径
		String caller = SsoTools.class.getName();
		//md5加密
		String m = Md5Encoder.getInstance().encode(user + pin);
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("entry", entry);
		params.put("user", user);
		params.put("m", m);
		params.put("caller", caller);
		
		String result = EAHttp.getTextByHttpWithPostWayByGBK(urls, params);
		System.out.println(result);
		if(StringUtils.isBlank(result)){
			return null;
		}
		String[] arr = result.split("&");
		if(arr == null || arr.length == 0){
			return null;
		}
		Map<String,String> map = new HashMap<String,String>();

		for(String units : arr){
			String[] unit = units.split("=");
			if(unit!=null && unit.length==2){
				map.put(unit[0], unit[1]);
			}
		}
		if(!"succ".equals(map.get("result")) || !"yes".equals(map.get("exist"))
				|| StringUtils.isBlank(map.get("uniqueid"))){
			return null;
		}
		String _uniqueId = map.get("uniqueid");
		Long uniqueId = null;
		try{
			uniqueId = Long.valueOf(_uniqueId);
		} catch(NumberFormatException e){
			uniqueId = null;
		}
		return uniqueId;
	}
}
