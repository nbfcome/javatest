package com.sina.ea.core.components.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.sina.ea.core.bo.Customer;
import com.sina.ea.core.bo.Permission;
import com.sina.ea.core.bo.Role;

public class EaSession implements Map<String, Object>, Serializable {

	
	/**
	 * #desc 在此添加字段说明
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	
	private static final long serialVersionUID = 719921614478415679L;
	private Session session;

	public EaSession() {
		Subject subject = SecurityUtils.getSubject();
		session = subject.getSession();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	public static final String CUSTOMER = "CURRENT_CUSTOMER";
	public static final String ROLE_LIST = "ROLE_LIST";
	public static final String PERMISSION_LIST = "PERMISSION_LIST";
	public static final String PROXIED_CUSTOMER = "PROXIED_CUSTOMER";
	/**
	 * 获取当前用户
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public Customer getCustomer() {
		Subject subject = SecurityUtils.getSubject();
		return (Customer) subject.getPrincipal();
	}

	/**
	 * 获取被代理的用户ID
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public Customer getProxiedCustomer() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (Customer) session.getAttribute(PROXIED_CUSTOMER);
	}
	
	/**
	 * 获取用户role列表
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public List<Role> getRoleList() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (List<Role>) session.getAttribute(ROLE_LIST);
	}
	
	/**
	 * 获取用户permission权限列表
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public List<Permission> getPermissionList() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return (List<Permission>) session.getAttribute(PERMISSION_LIST);
	}
	
	public void clear() {
		// TODO Auto-generated method stub
	}

	public boolean containsKey(Object key) {
		return get((String)key) != null;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object get(Object key) {
		return session.getAttribute((String) key);
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object put(String key, Object value) {
		session.setAttribute((String) key, value);
		return value;
	}

	public void putAll(Map<? extends String, ? extends Object> t) {
		for (Map.Entry<? extends String, ? extends Object> entry : t.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public Object remove(Object key) {
		return session.removeAttribute((String) key);
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}
}
