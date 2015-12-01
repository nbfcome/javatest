package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "ea_redirect")
public class Redirect extends BaseObject implements Serializable {

	/**
	 * WSH
	 */
	private static final long serialVersionUID = -2823863473294362036L;

	private Long id;

	private String redirectName;

	private String redirectDescription;

	private String orientation;

	private String redirectType;

	private Long customerId;

	private Long status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRedirectName() {
		return redirectName;
	}

	public void setRedirectName(String redirectName) {
		this.redirectName = redirectName;
	}

	public String getRedirectDescription() {
		return redirectDescription;
	}

	public void setRedirectDescription(String redirectDescription) {
		this.redirectDescription = redirectDescription;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
