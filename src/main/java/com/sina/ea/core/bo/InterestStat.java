package com.sina.ea.core.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EA_STAT_INTEREST")
public class InterestStat implements Serializable{
	
	private static final long serialVersionUID = -6177343742494407254L;
	private Long id;
	private Long statDate;
	private Long customerId;
	private String interest;
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
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
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
	public Integer getBidType() {
		return bidType;
	}
	public void setBidType(Integer bidType) {
		this.bidType = bidType;
	}
	
}
