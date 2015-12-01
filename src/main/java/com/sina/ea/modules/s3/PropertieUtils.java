/*
 * PROPRIETARY and CONFIDENTIAL
 * gina
 * @version V1.0
 * SINA Corporation, All Rights Reserved
 */
package com.sina.ea.modules.s3;

import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 *  Properties文件加载工具类 
 * PropertieUtils  
 * 
 * wujun2@staff.sina.com.cn 
 * 2012-4-17 下午3:52:02  
 *   
 */
public abstract class PropertieUtils {
	
	private Logger logger = LoggerFactory.getLogger(PropertieUtils.class);
	
	/**
	 * cache of configurations
	 */
	private Map<String, Configuration> configTable = new Hashtable<String, Configuration>();
	
	protected String fileName;
	
	public PropertieUtils(String fileName) {
		this.fileName = fileName;
	}
	protected String getString(String key) {
		Configuration config = getProperties(fileName);
		String v = config.getString(key);
		if (StringUtils.isNotEmpty(v)) return v;
		return "";
	}
	protected String[] getStringArray(String key) {
		Configuration config = getProperties(fileName);
		String[] vs = null;
		try {
			vs = config.getStringArray(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vs;
	}
	public static void setProperties(String key, String value, String fileName) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(fileName);
			config.addProperty(key, value);
			config.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 清空配置文件
	* @Title: clearProperties   
	* @Description: 
	* @param key
	* @param fileName
	* @return void 
	* @see PropertieUtils.java
	 */
	public static void clearProperties(String key, String fileName) {
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(fileName);
			config.clearProperty(key);
			config.refresh();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private Configuration getProperties(String fileName) {
		Configuration config = configTable.get(fileName);
		if (config == null) return load(fileName);
		return config;
	}

	private Configuration load(String fileName) {
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(fileName);
			if (config != null) {
				configTable.put(fileName, config);
			}
		} catch (ConfigurationException e) {
			logger.error("Can't find " + fileName, e);
		}
		return config;
	}
}
