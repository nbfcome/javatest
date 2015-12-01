package com.sina.ea.core.dao;

import org.springframework.stereotype.Repository;

import com.sina.adm.base.dao.GenericDao;
import com.sina.adm.base.dao.GenericPartitionDao;
import com.sina.ea.core.bo.AgeStat;

@Repository
public class AgeStatDao extends GenericPartitionDao<AgeStat, Long>{

}
