package com.sina.ea.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UUIDUtils {
	protected static Logger log = LoggerFactory.getLogger(UUIDUtils.class);

	/**
	 * 
	 * #func 获取uuid<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
		       + s.substring(19, 23) + s.substring(24);
	}
	
	public static void main(String args[]){
		System.out.println(UUIDUtils.getUUID());
	}
}