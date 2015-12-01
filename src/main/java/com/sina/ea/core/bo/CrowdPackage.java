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
@Table(name="EA_CROWD_PACKAGE")
public class CrowdPackage extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3745325946505071268L;
	private Long id;
	private Long customerId;
	private String cpId = "";
	private String cpName;
	private Long crowdCount = 0l;
	private String note;
	private Long dataAccess;
	private String visitorBehavior ="";
	private String interfaceSite ="";
	private Long frequency = 0l;
	private Long syncStatus;
	private Date syncTime = new Date();
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
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public Long getCrowdCount() {
		return crowdCount;
	}
	public void setCrowdCount(Long crowdCount) {
		this.crowdCount = crowdCount;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Long getDataAccess() {
		return dataAccess;
	}
	public void setDataAccess(Long dataAccess) {
		this.dataAccess = dataAccess;
	}
	public String getVisitorBehavior() {
		return visitorBehavior;
	}
	public void setVisitorBehavior(String visitorBehavior) {
		this.visitorBehavior = visitorBehavior;
	}
	public String getInterfaceSite() {
		return interfaceSite;
	}
	public void setInterfaceSite(String interfaceSite) {
		this.interfaceSite = interfaceSite;
	}
	public Long getFrequency() {
		return frequency;
	}
	public void setFrequency(Long frequency) {
		this.frequency = frequency;
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
