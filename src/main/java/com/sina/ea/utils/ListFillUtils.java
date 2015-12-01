package com.sina.ea.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sina.adm.base.utils.DateUtils;

public class ListFillUtils {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * #func list补零<br>
	 * #desc 将list在开始结束时间不存在的记录补空，其中需要list是升序的
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public static <T> List<T> zerofill(List<T> dataList, String startDate, String endDate, String dateFormat, String dateName, Class<T> entityClass) 
											throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		long totalDays = DateUtils.getDaySpan(DateUtils.parseDate(startDate, dateFormat),DateUtils.parseDate(endDate, dateFormat));
		if(Math.abs(totalDays) > 3*365){
			//超过3年的，不进行处理，直接返回原表
			return dataList;
		}
		
		List<T> zeroFillList = new ArrayList<T>();	
		if(dataList == null){
			dataList = new ArrayList<T>();
		}
		
		int i = 0;
		String currDate = startDate;
		String toDate = endDate;
		String orderStr = "asc";
		
		if(dataList.size() > 1){
			String start = BeanUtils.getProperty(dataList.get(0), dateName);
			String end = BeanUtils.getProperty(dataList.get(dataList.size()-1), dateName);
			long diff = DateUtils.getDaySpan(DateUtils.parseDate(start, dateFormat),DateUtils.parseDate(end, dateFormat));
			if(diff >= 0){
				//升序
				i = 0;
				currDate = startDate;
				toDate = endDate;
				orderStr = "asc";
			}else{
				//降序
				i = dataList.size()-1;
				currDate = endDate;
				toDate = startDate;
				orderStr = "desc";
				
			}
		}
		
		//从结束时间开始，如果当前时间比开始时间大，那么就继续循环
		while(true){
			long diff = DateUtils.getDaySpan(DateUtils.parseDate(toDate, dateFormat),DateUtils.parseDate(currDate, dateFormat));
			if("asc".equals(orderStr)){
				if(diff >0){
					break;
				}
			}else{
				if(diff <0){
					break;
				}
			}
			
			boolean isEnd = false;
			if("asc".equals(orderStr)){
				if(i>=dataList.size()){
					isEnd = true;
				}
			}else{
				if(i<=0){
					isEnd = true;
				}
			}
			if(!isEnd && currDate.equals(BeanUtils.getProperty(dataList.get(i), dateName))){
				zeroFillList.add(dataList.get(i));
				if("asc".equals(orderStr)){
					i++;	//后一条
				}else{
					i--;
				}
			}else{
				T emptyRecord = entityClass.newInstance();
				BeanUtils.setProperty(emptyRecord, dateName, currDate);		//填入日期
				zeroFillList.add(emptyRecord);		//插入0记录
			}
			//下一日
			if("asc".equals(orderStr)){
				currDate = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(currDate, dateFormat), 1), dateFormat);
			}else{
				currDate = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(currDate, dateFormat), -1), dateFormat);
			}
		}
		
		return zeroFillList;
	}
	
	/**
	 * #func list补零<br>
	 * #desc 将list在开始结束时间不存在的记录补空，其中需要list是升序的
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 */
	public static <T> List<T> zerofill(List<T> dataList, String startDate, String endDate,String dateName, Class<T> entityClass) 
											throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		return zerofill(dataList,startDate,endDate,"yyyyMMdd", dateName, entityClass);
	}
	
	/**
	 * #func list补零<br>
	 * #desc 将list在开始结束时间不存在的记录补空，其中需要list是升序的
	 *
	 * @author zhangzhibo
	 * @version 1.0.0
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 */
	public static <T> List<T> zerofill(List<T> dataList, String startDate, String endDate, Class<T> entityClass) 
											throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
		return zerofill(dataList,startDate,endDate,"yyyyMMdd", "countDate", entityClass);
	}
	
	
	public static void main(String args[]){
		System.out.println("testAsc():");
	}
}
