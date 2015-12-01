package com.sina.ea.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sina.ea.core.bo.Customer;
import com.sina.ea.core.bo.CustomerLog;
import com.sina.ea.core.components.session.EaSession;
import com.sina.ea.core.dao.CustomerLogDao;
import com.sina.ea.modules.exception.ServiceException;

@Service
public class CustomerLogService {

	@Autowired
	private CustomerLogDao customerLogDao;

	/**
	 * 
	 * #func 插入普通日志<br>
	 * #desc
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public void addCustomerLog(Long customerId, Long opCustomerId, String operation, String description,Long objectId,String objectName) {
		CustomerLog customerLog = new CustomerLog();
		customerLog.setCustomerId(customerId);
		customerLog.setOpCustomerId(opCustomerId);
		customerLog.setOperation(operation);
		customerLog.setDescription(description);
		customerLog.setObjectId(objectId);
		customerLog.setObjectName(objectName);
		customerLogDao.save(customerLog);
	}

	/**
	 * 
	 * #func 简化日志方法，自动设置操作人及代理人<br>
	 * #desc 该方法只能被登录的用户使用，api或者未登录情况禁止使用，否则无法获取session及用户id
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public void addCustomerLog(String operation, String description,Long objectId,String objectName) throws ServiceException {
		try {
			EaSession session = new EaSession();
			Customer customer = session.getCustomer();
			Customer proxiedCustomer = session.getProxiedCustomer();
			if (proxiedCustomer == null) {
				// 如果没有代理对象，则就是当前用户操作自己数据
				proxiedCustomer = customer;
			}
			addCustomerLog(proxiedCustomer.getCustomerId(), customer.getCustomerId(), operation, description,objectId,objectName);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
