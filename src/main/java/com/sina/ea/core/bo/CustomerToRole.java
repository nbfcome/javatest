package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_CUSTOMERTOROLE")
public class CustomerToRole extends BaseObject implements Serializable {

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	private static final long serialVersionUID = 916362038056312256L;

	private Long id;
	private Long customerId;
	private Long roleId;

	public CustomerToRole() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}