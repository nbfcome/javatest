package com.sina.ea.core.interceptor;

import java.util.concurrent.ConcurrentHashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PerfInterceptor implements MethodInterceptor {

	Logger logger = LoggerFactory.getLogger(PerfInterceptor.class.getName());
	
	private volatile boolean switchOn = true;

	private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();

	private static long statLogFrequency = 10;

	private static long methodWarningThreshold = 1000;

	public Object invoke(MethodInvocation method) throws Throwable {

		if(switchOn){
			
			long start = System.currentTimeMillis();

			try {

				return method.proceed();

			}

			finally {

				updateStats(method.getMethod().getName(),(System.currentTimeMillis() - start) ,method);

			}
		}else{
			return method.proceed();
		}
		

	}

	private void updateStats(String methodName, long elapsedTime, MethodInvocation method) {

		MethodStats stats = methodStats.get(methodName);

		if (stats == null) {

			stats = new MethodStats(methodName);

			methodStats.put(methodName, stats);

		}

		stats.count++;

		stats.totalTime += elapsedTime;

		if (elapsedTime > stats.maxTime) {

			stats.maxTime = elapsedTime;

		}
		
//		Object[] args = method.getArguments();
//		StringBuffer sb = new StringBuffer();
//		if(args != null){
//			for(int i =0 ; i<args.length;i++){
//				if(args[i]!= null){
//					sb.append(args[i].toString()).append(",");
//				}
//			}
//		}

		if (elapsedTime > methodWarningThreshold) {

			logger.info("WSH method warning: " + methodName + "(), cnt = " + stats.count 
					+ ", lastTime = " + elapsedTime + ", maxTime = " + stats.maxTime);

		}

		if (stats.count % statLogFrequency == 0) {

			long avgTime = stats.totalTime / stats.count;

			long runningAvg = (stats.totalTime - stats.lastTotalTime) / statLogFrequency;

			logger.info("WSH method: " + methodName + "(), cnt = " + stats.count
					+ ", lastTime = " + elapsedTime + ", avgTime = " + avgTime
					+ ", runningAvg = " + runningAvg + ", maxTime = "
					+ stats.maxTime);

			// reset the last total time

			stats.lastTotalTime = stats.totalTime;

		}

	}

	class MethodStats {

		public String methodName;

		public long count;

		public long totalTime;

		public long lastTotalTime;

		public long maxTime;

		public MethodStats(String methodName) {

			this.methodName = methodName;

		}

	}

	public boolean isSwitchOn() {
		return switchOn;
	}

	public void setSwitchOn(boolean switchOn) {
		this.switchOn = switchOn;
	}

}
