package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

/**
 * #func 系统 键值<br>
 * #desc
 * 
 * @author zhangzhibo
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "SYSNVTAB")
public class SysNvTab extends BaseObject implements Serializable {
	
	
	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 5800534092303162611L;
	private Long id;
	private String name;
	private String value;

	public SysNvTab(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public SysNvTab() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sysnvtabid")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
