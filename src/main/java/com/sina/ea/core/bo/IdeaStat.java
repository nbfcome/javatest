package com.sina.ea.core.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EA_STAT_IDEA")
public class IdeaStat implements Serializable{

	private static final long serialVersionUID = 3661557647344769702L;
	
	private Long id;
	private Long statDate;
	private Long customerId;
	private Long ideaId;
	private String ideaName;
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
	public String getIdeaName() {
		return ideaName;
	}
	public void setIdeaName(String ideaName) {
		this.ideaName = ideaName;
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
	public Long getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
