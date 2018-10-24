package com.wsy.fyxw.dao.factory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.dao.BaseDao;

@Component
@SuppressWarnings("rawtypes")
public class DaoFactory {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Map<String, BaseDao> daoMap;

	public BaseDao getDao(String name) {
		try {
			return daoMap.get(name);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
