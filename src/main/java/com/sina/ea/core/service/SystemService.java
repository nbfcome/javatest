package com.sina.ea.core.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sina.ea.core.bo.SysNvTab;
import com.sina.ea.core.dao.SysNvTabDao;


/**
 * 
 * #func 系统service类，封装多个系统级表操作，目前有SysNvTab表的操作<br>
 * #desc 在此添加实现相关说明
 * 
 * @author zhangzhibo
 * @version 1.0.0
 * 
 */
@Service
public class SystemService {
	private static final Logger logger = LoggerFactory.getLogger(SystemService.class.getName());

	@Autowired
	private SysNvTabDao sysNvTabDao;

	/**
	 * 
	 * #func 根据id获得键值对<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public SysNvTab getNv(Long sysNvTabId) {
		if (sysNvTabId == null) {
			return null;
		}
		return sysNvTabDao.get(sysNvTabId);
	}

	/**
	 * 
	 * #func 保存键值对，存在就更新，不存在就新增<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void saveNv(SysNvTab sysNvTab) {
		sysNvTabDao.save(sysNvTab);
	}

	/**
	 * 
	 * #func 删除键值对,0成功，-1不存在<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Long delNv(Long sysNvTabId) {
		SysNvTab sysNvTab = sysNvTabDao.get(sysNvTabId);
		if (sysNvTab == null) {
			return -1L;
		}
		sysNvTabDao.delete(sysNvTab);
		return 0L;
	}

	/**
	 * 
	 * #func 按名称删除键值对<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delNvByName(String sysNvTabName) {
		String hql = "delete from SysNvTab where name = ?";
		sysNvTabDao.execHQL(hql, sysNvTabName);
	}

	/**
	 * 
	 * #func 根据键获得键值对列表<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SysNvTab> getNvByName(String nvName) {
		Criteria c = sysNvTabDao.getSession().createCriteria(SysNvTab.class);
		c.add(Restrictions.eq("name", nvName));
		return c.list();
	}

	/**
	 * 
	 * #func 根据键获得唯一键值对<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public SysNvTab getNvTabByName(String nvName) {
		List<SysNvTab> sysNvTabList = getNvByName(nvName);
		if (CollectionUtils.isEmpty(sysNvTabList)) {
			logger.info("getNvTabByName. SysNvTabList is empty by nvname:" + nvName);
			return null;
		}
		return sysNvTabList.get(0);
	}

	/**
	 * 
	 * #func 根据值获得键值对列表<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public List<SysNvTab> getNvByValue(String nvValue) {
		Criteria c = sysNvTabDao.getSession().createCriteria(SysNvTab.class);
		c.add(Restrictions.eq("value", nvValue));
		return c.list();
	}

	/**
	 * 
	 * #func 根据键和值获得键值对<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	@SuppressWarnings("unchecked")
	public SysNvTab getNvByPair(String nvName, String nvValue) {
		Criteria c = sysNvTabDao.getSession().createCriteria(SysNvTab.class);
		c.add(Restrictions.eq("name", nvName));
		c.add(Restrictions.eq("value", nvValue));
		List<SysNvTab> list = c.list();
		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 
	 * #func 根据名获得键值对的值<br>
	 * #desc 
	 * 
	 * @author zhangzhibo
	 * @version 1.0.0
	 */
	public String getNvValueByName(String nvName) {
		String hql = "from SysNvTab where name=?";
		List<SysNvTab> list = sysNvTabDao.find(hql, nvName);
		if (list == null || list.size() == 0) {
			return "";
		} else {
			return list.get(0).getValue();
		}
	}

	/**
	 * 
	 * #func 根据键和值获得键值对，修改成功返回0,失败返回-1<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 4.0.0
	 */
	public Long editNv(String nvName, String nvValue) {
		String hql = "from SysNvTab where name = ?";
		SysNvTab sysNvTab = sysNvTabDao.findUnique(hql, nvName);

		if (sysNvTab != null) {
			sysNvTab.setValue(nvValue);
			sysNvTabDao.save(sysNvTab);
			return 0L;
		} else {
			return -1L;
		}
	}
}
