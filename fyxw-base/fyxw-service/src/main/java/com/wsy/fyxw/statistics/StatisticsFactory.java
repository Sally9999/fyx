package com.wsy.fyxw.statistics;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsFactory {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Map<String, Statistics> statisticsMap;

	public Statistics getStatistics(String name) {
		try {
			return statisticsMap.get(name);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return null;
	}

}
