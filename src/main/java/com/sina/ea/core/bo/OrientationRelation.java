package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;


@Entity
@Table(name = "EA_ORIENTATIONRELATION")
public class OrientationRelation extends BaseObject implements Serializable{

	private static final long serialVersionUID = -5524618438665534295L;
	/**
	 * #desc 在此添加字段说明
	 * @author zhouwei
	 * @version 1.0.0
	 */
	
	
	private Long id;
	private Long parentId;
	private String orientationName;
	private String orientationValue;
	
	public OrientationRelation(){
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String getOrientationName() {
		return orientationName;
	}


	public void setOrientationName(String orientationName) {
		this.orientationName = orientationName;
	}


	public String getOrientationValue() {
		return orientationValue;
	}


	public void setOrientationValue(String orientationValue) {
		this.orientationValue = orientationValue;
	}

}