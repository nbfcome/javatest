package com.sina.ea.utils;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.sina.adm.base.dao.Page;

@Component
public class SqlUtils {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SessionFactory sessionFactory;  
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Long getCountBySQL(String sql, Map<String, Object> values) {
		String fromSql = sql;
		fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
		fromSql = StringUtils.substringBefore(fromSql, "order by");
		fromSql = "select 1 " + fromSql;

		String countSql = "select count(1) as count from (" + fromSql + ") __table";

		try {
			@SuppressWarnings("deprecation")
			Long count = (Long)createSQLQuery(countSql, values).addScalar("count", Hibernate.LONG).uniqueResult();
			Long totalCount = 0L;
			if(count != null) {
				totalCount = count;
			}
			
			return totalCount;
		} catch (Exception e) {
			throw new RuntimeException("Sql can't be auto count, hql is:" + countSql, e);
		} 
	}
	
	public <T> SQLQuery createQueryBySQL(Page<T> page, String sql, Map<String, Object> values, Boolean isAll) {
		
		if(page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sql = sql + " ORDER BY " + page.getOrderBy() +  " ";
			if(page.getOrder() != null) {
				sql += page.getOrder();
			}
		}
		
		SQLQuery q = createSQLQuery(sql, values);
		if(!isAll) {
			q.setFirstResult(page.getFirst() - 1);
			q.setMaxResults(page.getPageSize());
		}
		
		return q;
	}
	
	public <T> SQLQuery createQueryBySQLEnhance(Page<T> page, String sql, Map<String, Object> values, Integer pageCount) {
		
		if(page.getOrderBy() != null && !"".equals(page.getOrderBy())) {
			sql = sql + " ORDER BY " + page.getOrderBy() +  " ";
			if(page.getOrder() != null) {
				sql += page.getOrder();
			}
		}
		
		SQLQuery q = createSQLQuery(sql, values);
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize() * pageCount);
		
		return q;
	}
	
	public SQLQuery createSQLQuery(String querySQL, Map<String,Object> parameterMap) {
		Assert.hasText(querySQL);
		SQLQuery queryObject = getSession().createSQLQuery(querySQL);
		if (parameterMap != null && parameterMap.size()>0) {
			Iterator<String> keyIterator=parameterMap.keySet().iterator();
			while(keyIterator.hasNext()){
				String key=keyIterator.next();
				Object value=parameterMap.get(key);
				queryObject.setParameter(key, value);
			}
		}
		return queryObject;
	}
	
	
}
