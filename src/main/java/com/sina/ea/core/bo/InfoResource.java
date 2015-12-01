package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;
/**
 * 信息流资源类
 * @author biaofei
 *
 */
@Entity
@Table(name = "EA_INFORESOURCE")
public class InfoResource extends BaseObject implements Serializable {

	private static final long serialVersionUID = -880722964167057086L;
	private Long id; //主键
	private String pdpsId; //虚拟资源id
	private String pdpsName; //虚拟资源名
	private String platform; //平台，英文：app、wap



	public InfoResource() {
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getPdpsId() {
		return pdpsId;
	}



	public void setPdpsId(String pdpsId) {
		this.pdpsId = pdpsId;
	}



	public String getPdpsName() {
		return pdpsName;
	}



	public void setPdpsName(String pdpsName) {
		this.pdpsName = pdpsName;
	}


	public String getPlatform() {
		return platform;
	}


	public void setPlatform(String platform) {
		this.platform = platform;
	}


	
}