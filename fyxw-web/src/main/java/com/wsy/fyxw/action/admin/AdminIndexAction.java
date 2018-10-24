package com.wsy.fyxw.action.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.enums.EnumStatisticsCommandType;
import com.wsy.fyxw.service.StatisticsService;

/**
 * 管理员首页
 * 
 * @author Sally
 *
 */
@RestController
public class AdminIndexAction extends BaseAction {

	@Autowired
	private StatisticsService statisticsService;

	@PostMapping("/stat/getData")
	public Map<String, Map<String, Object>> getStatisticsData() {
		return statisticsService.getStatisticsData(EnumStatisticsCommandType.ALL.getCode());
	}
}
