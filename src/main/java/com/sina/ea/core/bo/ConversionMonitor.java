package com.sina.ea.core.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name="EA_CONVERSION_MONITOR")
public class ConversionMonitor extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950258539623039447L;
	private Long id;
	private Long customerId;
	private String cmId;
	private String cmName;
	private String paramterName1;
	private String paramterName2;
	private String paramterName3;
	private Long syncStatus;
	private Date syncTime;
	private Long normalStatus;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCmId() {
		return cmId;
	}
	public void setCmId(String cmId) {
		this.cmId = cmId;
	}
	public String getCmName() {
		return cmName;
	}
	public void setCmName(String cmName) {
		this.cmName = cmName;
	}
	public String getParamterName1() {
		return paramterName1;
	}
	public void setParamterName1(String paramterName1) {
		this.paramterName1 = paramterName1;
	}
	public String getParamterName2() {
		return paramterName2;
	}
	public void setParamterName2(String paramterName2) {
		this.paramterName2 = paramterName2;
	}
	public String getParamterName3() {
		return paramterName3;
	}
	public void setParamterName3(String paramterName3) {
		this.paramterName3 = paramterName3;
	}
	public Long getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(Long syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Date getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}
	public Long getNormalStatus() {
		return normalStatus;
	}
	public void setNormalStatus(Long normalStatus) {
		this.normalStatus = normalStatus;
	}
	

}
