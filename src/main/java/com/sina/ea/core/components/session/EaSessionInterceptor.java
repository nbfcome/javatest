package com.sina.ea.core.components.session;

import java.lang.reflect.Field;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.sina.adm.base.components.session.MemApplicationAware;
import com.sina.adm.base.components.session.MemSessionAware;


public class EaSessionInterceptor extends MethodFilterInterceptor{


	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = -3678260184059799396L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {

//		绑定session
		EaSession session = new EaSession();
		ActionContext.getContext().setSession(session);
		
//		设置application
		EaApplication application = new EaApplication();
		ActionContext.getContext().setApplication(application);
		
		
		Object action = invocation.getAction();
		boolean awareSession = action instanceof MemSessionAware;
		boolean awareApplication = action instanceof MemApplicationAware;
		
		
		// 自动注入到MemSession和MemApplication对象
		if (awareSession) {
			Field[] fields = action.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (awareSession && field.getType() == EaSession.class) {
					field.setAccessible(true);
					field.set(action, session);
				}
				if (awareApplication && field.getType() == EaApplication.class) {
					field.setAccessible(true);
					field.set(action, application);
				}
			}
			Field[] superFields = action.getClass().getSuperclass().getDeclaredFields();
			for (Field superField : superFields) {
				if (awareSession && superField.getType() == EaSession.class) {
					superField.setAccessible(true);
					superField.set(action, session);
				}
				if (awareApplication && superField.getType() == EaApplication.class) {
					superField.setAccessible(true);
					superField.set(action, application);
				}
			}
		}
		
		return invocation.invoke();
	}

}
