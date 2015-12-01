package com.sina.ea.core.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.PatternMatchUtils;

import com.sina.ea.core.dao.sessionfactory.KADynamicSessionFactoryKey;

/**
 * KA信息流动态配置sessionFactory拦截器
 * @author songjuan
 *
 */
public class KADynamicSessionFactoryInterceptor  implements MethodInterceptor{

	/**
	 * 方法和使用数据源key的对应关系
	 */
	private Map<String, String> attributeSessionFactory = new HashMap<String, String>();
	/**
	 * 数据源key的存储控制器
	 */
	private KADynamicSessionFactoryKey sessionFactoryKey;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String methodName = invocation.getMethod().getName();
		String bestNameMatch = null;
		for (Iterator<String> it = this.attributeSessionFactory.keySet().iterator(); it.hasNext();) {
			String mappedName = it.next();
			if (isMatch(methodName, mappedName) && (bestNameMatch == null || bestNameMatch.length() <= mappedName.length())) {
				bestNameMatch = mappedName;
			}
		}
		String key = attributeSessionFactory.get(bestNameMatch);
		if ("EA".equalsIgnoreCase(key)) {
			sessionFactoryKey.setEaSessionFactoryKey();
		} else if ("GINA".equalsIgnoreCase(key)) {
			sessionFactoryKey.setGinaSessionFactoryKey();
		}else {
			sessionFactoryKey.setEaSessionFactoryKey();
		}
		Object result = invocation.proceed();
		sessionFactoryKey.clearKey();
		
		return result;
	}

	public void setAttributes(Map<String, String> attributeSessionFactory) {
		this.attributeSessionFactory = attributeSessionFactory;
	}

	private boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

	public void setSessionFactoryKey(KADynamicSessionFactoryKey sessionFactoryKey) {
		this.sessionFactoryKey = sessionFactoryKey;
	}

}
