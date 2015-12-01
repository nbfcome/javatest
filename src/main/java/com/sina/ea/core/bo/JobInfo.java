package com.sina.ea.core.bo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

@Entity
@Table(name = "JOBINFO")
public class JobInfo extends BaseObject implements Serializable {


	private static final long serialVersionUID = -880722964167057086L;
	private Long id;
	private Long minMoney;
	private Long maxMoney;
	private String place;
	private String experience;
	private Long education;
	private Long jobtype;
	private String jobattract;
	private String jobdescribe;
	private String scope;
	private Long scale;
	private String home;
	private String job;
	private String department; 
	private String company;
	private String companySite;
	private String develop;
	
	private Long lgid;
		public JobInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(Long minMoney) {
		this.minMoney = minMoney;
	}

	public Long getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Long maxMoney) {
		this.maxMoney = maxMoney;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Long getEducation() {
		return education;
	}

	public void setEducation(Long education) {
		this.education = education;
	}

	public Long getJobtype() {
		return jobtype;
	}

	public void setJobtype(Long jobtype) {
		this.jobtype = jobtype;
	}

	public String getJobattract() {
		return jobattract;
	}

	public void setJobattract(String jobattract) {
		this.jobattract = jobattract;
	}

	public String getJobdescribe() {
		return jobdescribe;
	}

	public void setJobdescribe(String jobdescribe) {
		this.jobdescribe = jobdescribe;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getScale() {
		return scale;
	}

	public void setScale(Long scale) {
		this.scale = scale;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Long getLgid() {
		return lgid;
	}

	public void setLgid(Long lgid) {
		this.lgid = lgid;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanySite() {
		return companySite;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	public String getDevelop() {
		return develop;
	}

	public void setDevelop(String develop) {
		this.develop = develop;
	}

	
}