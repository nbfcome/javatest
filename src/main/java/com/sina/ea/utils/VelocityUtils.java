package com.sina.ea.utils;

import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.EscapeTool;
import org.apache.velocity.tools.generic.MathTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author wuqiang
 */
public class VelocityUtils {
	private static VelocityUtils instance = null;
	private MathTool mathTool;
	private DateTool dateTool;
	private NumberTool numberTool;
	private EscapeTool escapeTool;
	private VelocityUtils() {
		mathTool = new MathTool();
		dateTool = new DateTool();
		numberTool = new NumberTool();
		escapeTool = new EscapeTool();
	}
	
	public static VelocityUtils getInstance() {
		if(instance == null) {
			instance = new VelocityUtils();
		}
		return instance;
	}
	
	/**
	 * @param vmFile ģ���ļ�
	 * @param context 
	 * @return String
	 * @throws Exception
	 */
	public String parse(String vmFile, VelocityContext context) throws Exception {

		Resource res = new ClassPathResource("velocity.properties");
        Velocity.init(res.getFile().getCanonicalPath());
		Template template = null;
		StringWriter sw = new StringWriter();
		template = Velocity.getTemplate(vmFile, "utf-8");
		template.merge( context, sw );
		
		return sw.toString();
	}
	
	/**
	 * @param vmFile ģ���ļ�
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public String parse(String vmFile, Map map) throws Exception {
		VelocityContext context = map2context(map);
		Resource res = new ClassPathResource("velocity.properties");
		//System.out.println(res.getFile().getCanonicalPath());
        Velocity.init(res.getFile().getCanonicalPath());
		//Velocity.init();
		Template template = null;
		StringWriter sw = new StringWriter();
		Resource file = new ClassPathResource(vmFile);
		Velocity.evaluate(context, sw, "", new InputStreamReader(file.getInputStream(),"utf-8"));
		//template = Velocity.getTemplate(vmFile, "GBK");
		//template.merge( context, sw );
		
		return sw.toString();
	}
	
	public String parseFromString(String templateStr, Map map) throws Exception {
		VelocityContext context = map2context(map);
		//Resource res = new ClassPathResource("velocity.properties");
		//Velocity.init(res.getFile().getCanonicalPath());
		Velocity.init();
		StringWriter sw = new StringWriter();
		Velocity.evaluate(context, sw, "", templateStr);
		
		return sw.toString();
	}
	
	/**
	 * @param map
	 * @return VelocityContext
	 */
	private VelocityContext map2context(Map map) {
		VelocityContext context = new VelocityContext();
		context.put("date", dateTool);
		context.put("math", mathTool);
		context.put("number", numberTool);
		context.put("escape", escapeTool);
		Set keySet = map.keySet();
		for(Iterator i = keySet.iterator(); i.hasNext();) {
			String key = (String)i.next();
			context.put(key, map.get(key));
		}
		return context;
	}

	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		map.put("myNumber", 25435345);
		System.out.println(VelocityUtils.getInstance().parseFromString("$!number.format('##,###',$myNumber)", map));
		
	}
}