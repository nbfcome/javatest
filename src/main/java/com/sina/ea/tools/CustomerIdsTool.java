package com.sina.ea.tools;

import java.util.regex.Pattern;


public class CustomerIdsTool {
	
	private static final String REGEX = "\\d{1,}|((\\d{1,},){0,}\\d{1,})";
	
	/**
	 * 监测id字符串是否合法
	 * 
	 * @param customerIds
	 * @return
	 * @author biaofei
	 */
	public static boolean check(String customerIds){
		if(org.apache.commons.lang.StringUtils.isBlank(customerIds)){
			return false;
		}
		boolean b = Pattern.matches(REGEX, customerIds.replace(" ", ""));
		return b;
	}
	
	/**
	 * 
	 * true
	 * false
	 * false
	 * false
	 * false
	 * @param args
	 */
//	public static void main(String args[]){
//		System.out.println(check(" 1 , 23 , 12 "));
//		System.out.println(check("1,23,asd"));
//		System.out.println(check("1,23,"));
//		System.out.println(check(""));
//		System.out.println(check(null));
//	}

}
