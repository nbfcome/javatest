package com.sina.ea.core.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_ADVERTGROUP")
public class AdvertGroup extends BaseObject implements Serializable {

	private static final long serialVersionUID = 467362815980580914L;

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author songjuan
	 * @version 1.0.0
	 */


	private Long groupId;
	private String groupName;
	private String platform;
	private Double dayBudget;
	private Long status;
	private Customer customer;
	private Date pauseTime;
	private Long pauseNum = 0L;
	private Long sendStatus = 0L;
	private String service_line_code = "PINPAI-CPC";

	public AdvertGroup() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Double getDayBudget() {
		return dayBudget;
	}

	public void setDayBudget(Double dayBudget) {
		this.dayBudget = dayBudget;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "customerId")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getPauseTime() {
		return pauseTime;
	}

	public void setPauseTime(Date pauseTime) {
		this.pauseTime = pauseTime;
	}

	public Long getPauseNum() {
		return pauseNum;
	}

	public void setPauseNum(Long pauseNum) {
		this.pauseNum = pauseNum;
	}

	public Long getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Long sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getService_line_code() {
		return service_line_code;
	}

	public void setService_line_code(String service_line_code) {
		this.service_line_code = service_line_code;
	}
	
}