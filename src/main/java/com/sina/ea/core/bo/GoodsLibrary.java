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
@Table(name="EA_GOODS_LIBRARY")
public class GoodsLibrary extends BaseObject implements Serializable{

	private Long id;
	private Long customerId;
	private String glId = "";
	private String glName;
	private Long goodsCount = 0l;
	private String feedSite;
	private Long frequency;
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
	public String getGlId() {
		return glId;
	}
	public void setGlId(String glId) {
		this.glId = glId;
	}
	public String getGlName() {
		return glName;
	}
	public void setGlName(String glName) {
		this.glName = glName;
	}
	public Long getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(Long goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getFeedSite() {
		return feedSite;
	}
	public void setFeedSite(String feedSite) {
		this.feedSite = feedSite;
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
