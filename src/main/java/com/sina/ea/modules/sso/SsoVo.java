package com.sina.ea.modules.sso;

import java.io.Serializable;

public class SsoVo implements Serializable{

	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 7095433220850801676L;
	
	
	private Boolean islogin;
	private String logintype;
	private UserInfo userinfo;
	public Boolean getIslogin() {
		return islogin;
	}
	public void setIslogin(Boolean islogin) {
		this.islogin = islogin;
	}
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	
	
}