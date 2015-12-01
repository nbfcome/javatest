package com.sina.ea.core.page;

import java.util.Map;

import com.sina.adm.base.dao.Page;

/**
 * KA信息流统计报表使用（为了查询合计数据）
 * @author songjuan
 *
 * @param <T>
 */
public class KAPage<T> extends Page<T> {

	//-- 构造函数 --//
	public KAPage() {
	}

	public KAPage(final int pageSize) {
		setPageSize(pageSize);
	}

	public KAPage(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}
	
	public  Map<String, Object>  sumValues ;

	public Map<String, Object> getSumValues() {
		return sumValues;
	}

	public void setSumValues(Map<String, Object> sumValues) {
		this.sumValues = sumValues;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}
