package com.sina.ea.core.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EA_STAT_ADVERT")
public class AdvertStat implements Serializable {

	private static final long serialVersionUID = -3262034678924493365L;

	private Long id;
	private Long statDate;
	private Long customerId;
	private Long advertId;
	private String advertName;
	private Integer bidType;
	private Long impression;
	private Long click;
	private BigDecimal pay;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStatDate() {
		return statDate;
	}
	public void setStatDate(Long statDate) {
		this.statDate = statDate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}
	public Integer getBidType() {
		return bidType;
	}
	public void setBidType(Integer bidType) {
		this.bidType = bidType;
	}
	public Long getImpression() {
		return impression;
	}
	public void setImpression(Long impression) {
		this.impression = impression;
	}
	public Long getClick() {
		return click;
	}
	public void setClick(Long click) {
		this.click = click;
	}
	public BigDecimal getPay() {
		return pay;
	}
	public void setPay(BigDecimal pay) {
		this.pay = pay;
	}
	public Long getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Long advertId) {
		this.advertId = advertId;
	}
}
