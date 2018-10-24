package com.wsy.fyxw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.enums.EnumStatisticsCommand;
import com.wsy.fyxw.service.StatisticsService;
import com.wsy.fyxw.statistics.Statistics;
import com.wsy.fyxw.statistics.StatisticsFactory;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsFactory statisticsFactory;

	@Override
	public Map<String, Map<String, Object>> getStatisticsData(String type) {
		Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>();
		ArrayList<String> commands = EnumStatisticsCommand.getCommandList(type);
		if (CollectionUtils.isNotEmpty(commands)) {
			for (String command : commands) {
				Statistics stat = statisticsFactory.getStatistics(command);
				Map<String, Object> map = stat.statistic();
				result.put(command, map);
			}
		}
		return result;
	}

}
