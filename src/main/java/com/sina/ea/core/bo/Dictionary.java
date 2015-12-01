package com.sina.ea.core.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "DICTIONARY")
public class Dictionary extends BaseObject implements java.io.Serializable {


	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 312724624325835834L;
	private Long dicId;
	private String dicName;
	private String dicValue;
	private String description;
	private List<DicItem> dicItems = new ArrayList<DicItem>(0);

	// Constructors

	/** default constructor */
	public Dictionary() {
	}

	/** minimal constructor */
	public Dictionary(String dicName) {
		this.dicName = dicName;
	}

	// Property accessors

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getdicId() {
		return dicId;
	}

	public void setdicId(Long dicId) {
		this.dicId = dicId;
	}

	public String getdicName() {
		return dicName;
	}

	public void setdicName(String dicName) {
		this.dicName = dicName;
	}

	public String getdicValue() {
		return dicValue;
	}

	public void setdicValue(String dicValue) {
		this.dicValue = dicValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "dicId", referencedColumnName = "dicId")
	@OrderBy("rank asc")
	public List<DicItem> getDicItems() {
		return dicItems;
	}

	public void setDicItems(List<DicItem> dicItems) {
		this.dicItems = dicItems;
	}

}