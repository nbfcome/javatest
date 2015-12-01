package com.sina.ea.modules.sso;

import java.io.Serializable;

/**
 * 
 * #func sso cookie字段信息<br>
 * #desc 具体字段含义见：http://wiki.intra.sina.com.cn/pages/viewpage.action?pageId=5517037
 *       如果sso字段含义有变化，需要同步修改
 *
 * @author zhangzhibo
 * @version 1.0.0
 *
 */
public class UserInfo implements Serializable{

	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = -6950653004162894700L;
	
	private Long cookieversion;
	private Long begintime;
	private Long expriedtime;
	private String d;
	private String i;
	private String us;
	private String vf;
	private String vt;
	private String ac;
	private String lt;
	private Long uniqueid;
	private String userid;
	private String appgroup;
	private String name;
	private String displayname;
	private String gender;
	private String paysign;
	private String email;
	private String dob;
	private String ln;
	private String os;
	private String fmp;
	private String lcp;
	public Long getCookieversion() {
		return cookieversion;
	}
	public void setCookieversion(Long cookieversion) {
		this.cookieversion = cookieversion;
	}
	public Long getBegintime() {
		return begintime;
	}
	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}
	public Long getExpriedtime() {
		return expriedtime;
	}
	public void setExpriedtime(Long expriedtime) {
		this.expriedtime = expriedtime;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getI() {
		return i;
	}
	public void setI(String i) {
		this.i = i;
	}
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	public String getVf() {
		return vf;
	}
	public void setVf(String vf) {
		this.vf = vf;
	}
	public String getVt() {
		return vt;
	}
	public void setVt(String vt) {
		this.vt = vt;
	}
	public String getAc() {
		return ac;
	}
	public void setAc(String ac) {
		this.ac = ac;
	}
	public String getLt() {
		return lt;
	}
	public void setLt(String lt) {
		this.lt = lt;
	}
	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAppgroup() {
		return appgroup;
	}
	public void setAppgroup(String appgroup) {
		this.appgroup = appgroup;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPaysign() {
		return paysign;
	}
	public void setPaysign(String paysign) {
		this.paysign = paysign;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getLn() {
		return ln;
	}
	public void setLn(String ln) {
		this.ln = ln;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getFmp() {
		return fmp;
	}
	public void setFmp(String fmp) {
		this.fmp = fmp;
	}
	public String getLcp() {
		return lcp;
	}
	public void setLcp(String lcp) {
		this.lcp = lcp;
	}
	
	
}