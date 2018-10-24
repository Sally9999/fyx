package com.wsy.fyxw.service;

import java.util.Map;

public interface StatisticsService {

	/**
	 * 统计
	 * 
	 * @param type
	 * @return
	 */
	public Map<String,Map<String,Object>> getStatisticsData(String type);

}
