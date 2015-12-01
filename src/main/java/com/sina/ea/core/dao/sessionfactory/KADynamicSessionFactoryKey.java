package com.sina.ea.core.dao.sessionfactory;

import org.apache.log4j.Logger;

/**
 * 动态配置sessionFactoryKey
 * @author songjuan
 *
 */
public class KADynamicSessionFactoryKey {


	private static final Logger log = Logger.getLogger(KADynamicSessionFactoryKey.class);
	
	/**
	 * sessionfactory key的分线程存储变量
	 */
	private static final ThreadLocal<String> SF_KEY = new ThreadLocal<String>();
	
	/**
	 * eaSessionFactoryKey
	 */
	public String eaSessionFactoryKey;
	
	/**
	 * 访问ginaSessionFactoryKey
	 */
	public String ginaSessionFactoryKey;


	
	public String getEaSessionFactoryKey() {
		return eaSessionFactoryKey;
	}

	public void setEaSessionFactoryKey(String eaSessionFactoryKey) {
		this.eaSessionFactoryKey = eaSessionFactoryKey;
	}

	public String getGinaSessionFactoryKey() {
		return ginaSessionFactoryKey;
	}

	public void setGinaSessionFactoryKey(String ginaSessionFactoryKey) {
		this.ginaSessionFactoryKey = ginaSessionFactoryKey;
	}

	/**
	 * 设置商业门户sessionFactorykey
	 */
	public void setGinaSessionFactoryKey() {
		SF_KEY.set(ginaSessionFactoryKey);
		log.debug("set sessionFactoryKey------[" + SF_KEY.get() + "]");
	}


	/**
	 * 设置eaSessionFactoryKey
	 */
	public void setEaSessionFactoryKey() {
		SF_KEY.set(eaSessionFactoryKey);
		log.debug("set sessionFactoryKey------[" + SF_KEY.get() + "]");
	}



	public String getKey() {
		if (SF_KEY.get() == null) {
			setEaSessionFactoryKey();
		}
		String key = SF_KEY.get();
		log.debug("get sessionFactoryKey------[" + SF_KEY.get() + "]");
		return key;
	}
	

	
	/**
	 * 线程结束的时候清理线程变量
	 */
	public void clearKey() {
		SF_KEY.remove();
	}
	
}
