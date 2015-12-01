package com.sina.ea;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板尺寸对应类
 * @author biaofei
 *
 */
public class TemplateSizeMap {
	
	public static Map<String,String> templateSize = new HashMap<String, String>();
	
	static{
		templateSize.put("31", "160*120");
		templateSize.put("36", "160*120");
		templateSize.put("37", "160*120");
		templateSize.put("38", "160*120");
		templateSize.put("39", "620*408");
		templateSize.put("61", "152*112");
	}
}
