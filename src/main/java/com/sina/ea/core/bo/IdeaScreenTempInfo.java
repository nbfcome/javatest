package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ea_idea_screen_tempinfo")
public class IdeaScreenTempInfo implements Serializable {
	private Long custmerId;
	private Long ideaId;
	private String pdps;
	private String pageUrl;
	private String createTime;
	@Id
	public Long getCustmerId() {
		return custmerId;
	}
	public void setCustmerId(Long custmerId) {
		this.custmerId = custmerId;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
