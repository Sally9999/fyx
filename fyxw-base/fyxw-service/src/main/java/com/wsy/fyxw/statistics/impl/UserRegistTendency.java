package com.wsy.fyxw.statistics.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.dao.UserDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.statistics.Statistics;

@Component("userRegistTendency")
public class UserRegistTendency implements Statistics {

	@Value("${datasource.type}")
	private String DATA_SOURCE_TYPE;

	@Autowired
	private DaoFactory daoFactory;

	@Override
	public Map<String, Object> statistic() {
		UserDao userDao = (UserDao) daoFactory.getDao(DATA_SOURCE_TYPE + "UserDao");
		List<Map<String, String>> tendency = userDao.statUserRegistTendency();
		List<String> dates = new ArrayList<String>();
		List<String> counts = new ArrayList<String>();
		Map<String, Object> stat = new HashMap<String, Object>();
		if (CollectionUtils.isNotEmpty(tendency)) {
			for (Map<String, String> map : tendency) {
				dates.add(map.get("date"));
				counts.add(String.valueOf(map.get("count")));
			}
		}
		stat.put("xAxis", dates);
		stat.put("data", counts);
		return stat;
	}

}
