package com.sina.ea.core.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "EA_PERMISSION")
public class Permission extends BaseObject implements java.io.Serializable {

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	private static final long serialVersionUID = 5708680447930811563L;

	private Long permissionId;
	private String permissionName;
	private Long permissionType;
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public Long getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Long permissionType) {
		this.permissionType = permissionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("permissionId", getPermissionId()).toString();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Permission)) {
			return false;
		}
		Permission castOther = (Permission) other;
		return new EqualsBuilder().append(getPermissionId(), castOther.getPermissionId()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getPermissionId()).toHashCode();
	}
}