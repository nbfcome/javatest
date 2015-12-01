package com.sina.ea.core.dao.transaction;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import com.sina.ea.core.dao.sessionfactory.KADynamicSessionFactoryImpl;

/**
 * 为KA信息流可以访问gina数据库动态处理事务
 * @author songjuan
 *
 */
public class KADynamicTransactionManager extends HibernateTransactionManager {
 
    private static final long serialVersionUID = -4655721479296819154L;
    
    /** 
     * @see org.springframework.orm.hibernate3.HibernateTransactionManager#getDataSource()
     * @author songjuan
     */
    @Override
    public DataSource getDataSource() {
        return SessionFactoryUtils.getDataSource(getSessionFactory());
    }
 
    /** 
     * @see org.springframework.orm.hibernate3.HibernateTransactionManager#getSessionFactory()
     * @author songjuan
     */
    @Override
    public SessionFactory getSessionFactory() {
    	KADynamicSessionFactoryImpl dynamicSessionFactory = (KADynamicSessionFactoryImpl) super.getSessionFactory();  
        SessionFactory hibernateSessionFactory = dynamicSessionFactory.getHibernateSessionFactory();  
        return hibernateSessionFactory;  
    }
}