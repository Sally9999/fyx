package com.wsy.fyxw.statistics.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.dao.UserDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.query.UserQuery;
import com.wsy.fyxw.statistics.Statistics;

@Component("userCount")
public class UserCount implements Statistics {

	@Value("${datasource.type}")
	private String DATA_SOURCE_TYPE;

	@Autowired
	private DaoFactory daoFactory;

	@Override
	public Map<String, Object> statistic() {
		UserDao userDao = (UserDao) daoFactory.getDao(DATA_SOURCE_TYPE + "UserDao");
		long count = userDao.getCount(new UserQuery());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		return map;
	}

}
