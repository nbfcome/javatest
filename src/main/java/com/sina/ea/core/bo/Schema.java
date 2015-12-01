package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;
@Entity
@Table(name="ea_schema")
public class Schema extends BaseObject implements Serializable {

	/**
	 *  qiaozhenpeng
	 */
	private static final long serialVersionUID = -2823863473294362036L;
	
	private Long id;
	
	private String schemaName;
	
	private String LaunchTime;
	
	private String adposIdList;
	
	private String orientation;
	
	private String schemaType;
	
	private Long customerId;
	
	private Long status ;
	
	private Long advertType;

	
	public Long getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Long advertType) {
		this.advertType = advertType;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getLaunchTime() {
		return LaunchTime;
	}

	public void setLaunchTime(String launchTime) {
		LaunchTime = launchTime;
	}

	

	public String getAdposIdList() {
		return adposIdList;
	}

	public void setAdposIdList(String adposIdList) {
		this.adposIdList = adposIdList;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getSchemaType() {
		return schemaType;
	}

	public void setSchemaType(String schemaType) {
		this.schemaType = schemaType;
	}

}
