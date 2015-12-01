package com.sina.ea.core.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PostInterceptor implements MethodInterceptor{

	private static Logger logger = LoggerFactory.getLogger(PostInterceptor.class);
	
	private final static String POST = "post";
	private Object result = null;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
//		if(POST.equals(methodName)){
		String className = invocation.getMethod().getClass().getName();
		long start = System.nanoTime();
		result = invocation.proceed();
		long end = System.nanoTime();
		Object[] args = invocation.getArguments();
		StringBuffer sb = new StringBuffer();
		if(args != null){
			for(int i =0 ; i<args.length;i++){
				if(args[i]!= null){
					sb.append(args[i].toString()).append(",");
				} else{
					sb.append("null").append(",");
				}
			}
		}
		logger.info("PostInterceptor.invoke===="+className+"."+methodName+"("+sb.toString()+")=====take time:"+((end-start)/1000000000f));
		//		}
		return result;
	}
	
//	public static void test(){
//		logger.info(POST);
//	}

}
