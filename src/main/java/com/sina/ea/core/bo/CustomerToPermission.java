package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_CUSTOMERTOPERMISSION")
public class CustomerToPermission extends BaseObject implements Serializable {


	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 6450565511846739440L;
	
	private Long id;
	private Long customerId;
	private Long permissionId;
	//表示用户通过角色得到的Permission后，少拥有或者多拥有的额外的Permission
	//0:少拥有Permission，1：多拥有Permission
	private Long type;

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

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

}