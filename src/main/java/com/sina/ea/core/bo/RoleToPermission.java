package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_ROLETOPERMISSION")
public class RoleToPermission extends BaseObject implements Serializable {


	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 7474094767387820259L;
	
	private Long id;
	private Long roleId;
	private Long permissionId;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

}