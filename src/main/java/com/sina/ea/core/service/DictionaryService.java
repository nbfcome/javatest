package com.sina.ea.core.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sina.ea.core.bo.DicItem;
import com.sina.ea.core.bo.Dictionary;
import com.sina.ea.core.dao.DicItemDao;
import com.sina.ea.core.dao.DictionaryDao;


@Service
public class DictionaryService {
	static final Logger logger = LoggerFactory.getLogger(DictionaryService.class.getName());
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private DicItemDao dicItemDao;

	/**
	 * 
	 * #func 保存Dictionary<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public Dictionary saveDictionary(Dictionary dictionary) {
		if(dictionary == null){
			return null;
		}
		return dictionaryDao.save(dictionary);
	}
	
	/**
	 * 
	 * #func 获得一种类别的dicitem<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public List<DicItem> getDictItemsByDicName(String dicName) {
		Criterion nameEq = Restrictions.eq("dicName", dicName);
		List<Dictionary> dictList = dictionaryDao.find(nameEq);
		List<DicItem> dicItems = new ArrayList<DicItem>();
		if (dictList != null && dictList.size() > 0 && dictList.get(0).getDicItems() != null) {
			dicItems.addAll(dictList.get(0).getDicItems());
		}
		return dicItems;
	}

	/**
	 * 
	 * #func 获得一种类别的dicitem 并且按照itemvalue进行排序<br>
	 * #desc 将Itemvalue按照数字进行排序，使用时按照自己需求来用
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public List<DicItem> getDictItemsOrderByItemvalueByDicName(String dicName) {
		String hql = "select item from DicItem item,Dictionary dic where dic.dicId=item.dicid and dic.dicName=? order by item.rank asc,(item.itemvalue+0) asc";
		return dictionaryDao.find(hql, dicName);
	}

	/**
	 * #func 按照字典名和字典项值获取字典项<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public List<DicItem> findItemNameByItemValue(String dicName, String dicItemValue) {
		String hql = "select item from DicItem item,Dictionary dic where dic.dicId=item.dicid and dic.dicName=? and item.itemvalue=?";
		return dictionaryDao.find(hql, dicName, dicItemValue);
	}
	
	/**
	 * #func 按照字典名和字典项名称获取字典项<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public List<DicItem> findItemNameByItemName(String dicName, String dicItemName) {
		String hql = "select item from DicItem item,Dictionary dic where dic.dicId=item.dicid and dic.dicName=? and item.itemname=?";
		return dictionaryDao.find(hql, dicName, dicItemName);
	}

	/**
	 * #func 获取字典项value和name的映射关系<br>
	 * #desc 在此添加实现相关说明
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public Map<String, String> findItemMapByDicName(String dicName) {
		List<DicItem> list = getDictItemsByDicName(dicName);
		Map<String, String> retMap = new HashMap<String, String>();
		for (DicItem item : list) {
			retMap.put(item.getItemvalue(), item.getItemname());
		}
		return retMap;
	}

	/**
	 * #func 按照字典名获取字典对象<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public Dictionary getDictionaryByName(String dicName) {
		List<Dictionary> dictionary = dictionaryDao.findByCriteria(Restrictions.eq("dicName", dicName));
		if (CollectionUtils.isEmpty(dictionary)) {
			logger.info("getDictionaryByName dictionary is empty the dicName is" + dicName);
			return null;
		}
		return dictionary.get(0);
	}

	/**
	 * #func 通过字典项编号获取字典项<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public DicItem getDictItemById(Long dicitemid) {
		if (null == dicitemid) {
			return null;
		}
		return dicItemDao.get(dicitemid);
	}

	/**
	 * #func 通过字典项编号删除字典项<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public boolean deleteDictItemById(Long dicitemid) {
		if (null == dicitemid) {
			return false;
		}
		dicItemDao.delete(dicitemid);
		return true;
	}

	/**
	 * #func 获取同一dicid所有字典项的最大itemvalue值<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public Integer getDictItemMaxValue(String dicName) {
		if (StringUtils.isBlank(dicName)) {
			return 0;
		}
		String sql = "select ifnull(max(cast(i.itemvalue as UNSIGNED)),0) from dictionary d, dicitem i " +
				" where d.dicName = :dicName and d.dicid=i.dicid";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dicName", dicName);
		List<BigInteger> list = dicItemDao.findBySQL(sql, paramMap);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}else{
			return 0;
		}
	}

	/**
	 * #func 保存字典项纪录<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public DicItem saveDicItem(DicItem dicItem) {
		if (null == dicItem) {
			return null;
		}
		return dicItemDao.save(dicItem);
	}

	/**
	 * #func 判断字典项记录名称是否存在<br>
	 * 
	 * @author zhangzhibo
	 * @version 1.0
	 */
	public boolean isItemNameExsit(Long dicid, String itemname) {
		if (StringUtils.isBlank(itemname) || null == dicid) {
			return true;
		}
		List<DicItem> dicItems = dicItemDao
				.find(Restrictions.eq("dicid", dicid), Restrictions.eq("itemname", itemname));
		if (CollectionUtils.isEmpty(dicItems)) {
			logger.info("isItemNameExsit dicItems is empty. dicid:" + dicid.toString() + ",itemname;" + itemname);
			return false;
		}
		return true;
	}
}
