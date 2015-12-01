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
@Table(name="EA_RECOMMEND_GOODS")
public class RecommendGoods extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5303639308080680627L;
	/**
	 * 
	 */
	private Long customerId;
	private Long syncStatus;
	private Date syncTime;
	private Long normalStatus;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
