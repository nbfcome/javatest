package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;
@Entity
@Table(name="ea_idea_screen")
public class IdeaScreen extends BaseObject implements Serializable {
	private Long id;
	private Long ideaId;
	private String pdps;
	private String pageUrl;
	private String screenShotUrl;
	private long type;
	private String adposName;
	public String getAdposName() {
		return adposName;
	}
	public void setAdposName(String adposName) {
		this.adposName = adposName;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}
	public String getPdps() {
		return pdps;
	}
	public void setPdps(String pdps) {
		this.pdps = pdps;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getScreenShotUrl() {
		return screenShotUrl;
	}
	public void setScreenShotUrl(String screenShotUrl) {
		this.screenShotUrl = screenShotUrl;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
}
