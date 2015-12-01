package com.sina.ea.core.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;


@Entity
@Table(name = "RESOURCELEVEL")
public class ResourceLevel extends BaseObject implements Serializable{

	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 2255061507306231646L;
	private Long levelId;
	private String levelName;
	private Long fieldId;
	private BigDecimal aePrice;
	private BigDecimal dspPrice;
	private BigDecimal ampPrice;
	private String description;
	private String dspWhiteList;
	private String levelRight;
	
	public ResourceLevel(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public BigDecimal getAePrice() {
		return aePrice;
	}

	public void setAePrice(BigDecimal aePrice) {
		this.aePrice = aePrice;
	}

	public BigDecimal getDspPrice() {
		return dspPrice;
	}

	public void setDspPrice(BigDecimal dspPrice) {
		this.dspPrice = dspPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDspWhiteList() {
		return dspWhiteList;
	}

	public void setDspWhiteList(String dspWhiteList) {
		this.dspWhiteList = dspWhiteList;
	}

	public BigDecimal getAmpPrice() {
		return ampPrice;
	}

	public void setAmpPrice(BigDecimal ampPrice) {
		this.ampPrice = ampPrice;
	}

	public String getLevelRight() {
		return levelRight;
	}

	public void setLevelRight(String levelRight) {
		this.levelRight = levelRight;
	}
	
	
}