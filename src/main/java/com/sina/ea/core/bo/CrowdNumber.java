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
@Table(name="EA_CROWD_NUMBER")
public class CrowdNumber extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7667423693896045009L;
	/**
	 * 
	 */
	private Long id;
	private Long number;
	private String cpid;
	private Date statisticaltime;
	private Long normalStatus;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public Date getStatisticaltime() {
		return statisticaltime;
	}
	public void setStatisticaltime(Date statisticaltime) {
		this.statisticaltime = statisticaltime;
	}
	public Long getNormalStatus() {
		return normalStatus;
	}
	public void setNormalStatus(Long normalStatus) {
		this.normalStatus = normalStatus;
	}
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	
}
