package com.sina.ea.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatDataTypeConvertUtils {
	
	public static BigDecimal toBigDecimal(Object obj, Long defaults) {
		return (obj != null) ? new BigDecimal(obj.toString()) : new BigDecimal(defaults);
	}

//	public static Date toDate(Object obj, String defaults) {
//		try {
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//			return (obj != null) ? sf.parse(obj.toString()) : sf.parse(defaults);
//		}catch (ParseException e) {
//			return null;
//		}
//	}
	
	public static Long toLong(Object obj, Long defaults) {
		return (obj != null) ? new Long(obj.toString()) : defaults;
	}
	
	public static Integer toInteger(Object obj, Integer defaults) {
		return (obj != null) ? new Integer(obj.toString()) : defaults;
	}
}
