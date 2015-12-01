package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;


@Entity
@Table(name = "EA_IDEA")
public class IdeaJob extends BaseObject implements Serializable{

	/**
	 * @author WSH
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = -1294425008660817168L;
	
	private Long ideaId;
	private String ideaName;
	private Advert advert;
	private String wdht;
	private Long ideaType;
	private Long size;
	private Long flashVersion;
	private String ideaContent;
	private String clickUrl;
	private String html;
	private String previewUrl;
	private String qualificationUrls;
	private Long auditStatus;
	private String refuseContent;
	private Long status;
	private Long sendStatus = 0L;
	private String ideaTitle;
	
	//add by songjuan 2014-9-11 创意质量提醒功能新增字段isAdviseUpdate、ideaQualityJson
	//是否建议修改
	private Long isAdviseUpdate = 0L;
	//返回json信息
	private String ideaQualityJson = "";
	private Long ideaIgnore = 1L;
	
	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public IdeaJob(){
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}

	public String getIdeaName() {
		return ideaName;
	}

	public void setIdeaName(String ideaName) {
		this.ideaName = ideaName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "advertId")
	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public String getWdht() {
		return wdht;
	}

	public void setWdht(String wdht) {
		this.wdht = wdht;
	}

	public String getIdeaContent() {
		return ideaContent;
	}

	public void setIdeaContent(String ideaContent) {
		this.ideaContent = ideaContent;
	}

	public Long getIdeaType() {
		return ideaType;
	}

	public void setIdeaType(Long ideaType) {
		this.ideaType = ideaType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getFlashVersion() {
		return flashVersion;
	}

	public void setFlashVersion(Long flashVersion) {
		this.flashVersion = flashVersion;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getQualificationUrls() {
		return qualificationUrls;
	}

	public void setQualificationUrls(String qualificationUrls) {
		this.qualificationUrls = qualificationUrls;
	}

	public Long getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Long auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getRefuseContent() {
		return refuseContent;
	}

	public void setRefuseContent(String refuseContent) {
		this.refuseContent = refuseContent;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Long sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Long getIsAdviseUpdate() {
		return isAdviseUpdate;
	}

	public void setIsAdviseUpdate(Long isAdviseUpdate) {
		this.isAdviseUpdate = isAdviseUpdate;
	}

	public String getIdeaQualityJson() {
		return ideaQualityJson;
	}

	public void setIdeaQualityJson(String ideaQualityJson) {
		this.ideaQualityJson = ideaQualityJson;
	}

	public Long getIdeaIgnore() {
		return ideaIgnore;
	}

	public void setIdeaIgnore(Long ideaIgnore) {
		this.ideaIgnore = ideaIgnore;
	}

}