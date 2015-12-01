package com.sina.ea.core.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EA_TOTAL_ADVERT")
public class AdvertTotal implements Serializable {

	private static final long serialVersionUID = -3262034678924493367L;

	private Long id;
	private Long advertId;
	private String advertName;
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
	
	public String getAdvertName() {
		return advertName;
	}
	public void setAdvertName(String advertName) {
		this.advertName = advertName;
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
