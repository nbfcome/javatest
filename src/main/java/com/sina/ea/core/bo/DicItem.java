package com.sina.ea.core.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sina.adm.base.bo.BaseObject;

/**
 * #func 用户字典<br>
 * #desc
 * 
 * @author zhangzhibo
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "DICITEM")
public class DicItem extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -4944283852753458855L;

	private Long dicitemid;
	private Long dicid;
	private String itemname;
	private String itemvalue;
	private Long rank;

	// Constructors

	/** default constructor */
	public DicItem() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDicitemid() {
		return dicitemid;
	}

	public void setDicitemid(Long dicitemid) {
		this.dicitemid = dicitemid;
	}

	public Long getDicid() {
		return dicid;
	}

	public void setDicid(Long dicid) {
		this.dicid = dicid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemvalue() {
		return itemvalue;
	}

	public void setItemvalue(String itemvalue) {
		this.itemvalue = itemvalue;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

}