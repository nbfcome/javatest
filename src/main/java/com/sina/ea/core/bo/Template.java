package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

/**
 * 模板类
 * 
 * @author biaofei
 *
 */
@Entity
@Table(name = "CREATIVE_TEMPLATE")
public class Template extends BaseObject implements Serializable {

	
	private static final long serialVersionUID = -4286833778525175028L;
	
	private Long id;
	private Long type;
	private String datagrid;
	private String label;
	private Long issub;
	private Long templateid;
	private Long categoryid;
	private Long manual;
	
	public Template() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(String datagrid) {
		this.datagrid = datagrid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getIssub() {
		return issub;
	}

	public void setIssub(Long issub) {
		this.issub = issub;
	}

	public Long getTemplateid() {
		return templateid;
	}

	public void setTemplateid(Long templateid) {
		this.templateid = templateid;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public Long getManual() {
		return manual;
	}

	public void setManual(Long manual) {
		this.manual = manual;
	}

	

}