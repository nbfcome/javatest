package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;
import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_CUSTOMER")
public class Customer extends BaseObject implements Serializable {

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	private static final long serialVersionUID = -1422894689023241008L;

	private Long customerId;
	private Long ssoId;
	private String ssoName;
	private String realName;
	private Long customerType = 0L;
	private Long status = 0L;

	public Customer() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSsoId() {
		return ssoId;
	}

	public void setSsoId(Long ssoId) {
		this.ssoId = ssoId;
	}

	public String getSsoName() {
		return ssoName;
	}

	public void setSsoName(String ssoName) {
		this.ssoName = ssoName;
	}
	
	public Long getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Long customerType) {
		this.customerType = customerType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ssoName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Customer) {
			Customer tmp = (Customer) obj;
			if (tmp.getCustomerId().longValue() == this.getCustomerId().longValue()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(customerId);
	}
}