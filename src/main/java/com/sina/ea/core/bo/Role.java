package com.sina.ea.core.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_ROLE")
public class Role extends BaseObject implements java.io.Serializable {

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	private static final long serialVersionUID = 1737357253840089652L;

	private Long roleId;
	private String roleName;
	private Long roleType;
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleType() {
		return roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}