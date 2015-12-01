package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "ADPOS")
public class Adpos extends BaseObject implements Serializable {

	/**
	 * #desc 在此添加字段说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */

	private static final long serialVersionUID = -880722964167057086L;
	private Long id;
	private String adposId;
	private String adposName;
	private Long width;
	private Long height;
	private String wdht;
	private String gina;
	private String nad;
	private Long rotateNum;
	private Long levelId;
	private Long status;
	private String networkInfo;
	private String previewUrl;
	private String channel;
	private Long ideaNum;
	private String adposType;
	private String adposRight;
	private String platform;
	private String screenIndex;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Adpos() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdposId() {
		return adposId;
	}

	public void setAdposId(String adposId) {
		this.adposId = adposId;
	}

	public String getAdposName() {
		return adposName;
	}

	public void setAdposName(String adposName) {
		this.adposName = adposName;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getGina() {
		return gina;
	}

	public void setGina(String gina) {
		this.gina = gina;
	}

	public String getNad() {
		return nad;
	}

	public Long getRotateNum() {
		return rotateNum;
	}

	public void setRotateNum(Long rotateNum) {
		this.rotateNum = rotateNum;
	}

	public void setNad(String nad) {
		this.nad = nad;
	}

	public String getWdht() {
		return wdht;
	}

	public void setWdht(String wdht) {
		this.wdht = wdht;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(String networkInfo) {
		this.networkInfo = networkInfo;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getIdeaNum() {
		return ideaNum;
	}

	public void setIdeaNum(Long ideaNum) {
		this.ideaNum = ideaNum;
	}

	public String getAdposType() {
		return adposType;
	}

	public void setAdposType(String adposType) {
		this.adposType = adposType;
	}

	public String getAdposRight() {
		return adposRight;
	}

	public void setAdposRight(String adposRight) {
		this.adposRight = adposRight;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getScreenIndex() {
		return screenIndex;
	}

	public void setScreenIndex(String screenIndex) {
		this.screenIndex = screenIndex;
	}

}