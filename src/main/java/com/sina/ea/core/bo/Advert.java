package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_ADVERT")
public class Advert extends BaseObject implements Serializable {

	private static final long serialVersionUID = -7443353494910067185L;
	
	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhouwei
	 * @version 1.0.0
	 */


	private Long advertId;
	private String advertName;
	private AdvertGroup advertGroup;
	private Long bidType;
	private Double bidPrice;
	private Long advertType;
	private String adposIdList;
	private String launchTime;
	private String orientation;
	private Long status;
	private Long sendStatus = 0L;
	private Long isRetargeting;
	

	public Advert() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getAdvertId() {
		return advertId;
	}


	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}


	public String getAdvertName() {
		return advertName;
	}


	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	@ManyToOne
	@JoinColumn(name = "groupId")
	public AdvertGroup getAdvertGroup() {
		return advertGroup;
	}


	public void setAdvertGroup(AdvertGroup advertGroup) {
		this.advertGroup = advertGroup;
	}


	public Long getBidType() {
		return bidType;
	}


	public void setBidType(Long bidType) {
		this.bidType = bidType;
	}


	public Double getBidPrice() {
		return bidPrice;
	}


	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Long getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Long advertType) {
		this.advertType = advertType;
	}

	public String getAdposIdList() {
		return adposIdList;
	}


	public void setAdposIdList(String adposIdList) {
		this.adposIdList = adposIdList;
	}


	public String getLaunchTime() {
		return launchTime;
	}


	public void setLaunchTime(String launchTime) {
		this.launchTime = launchTime;
	}


	public String getOrientation() {
		return orientation;
	}


	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}


	public Long getStatus() {
		return status;
	}


	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Long sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Long getIsRetargeting() {
		return isRetargeting;
	}

	public void setIsRetargeting(Long isRetargeting) {
		this.isRetargeting = isRetargeting;
	}	

	
}