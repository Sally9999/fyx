package com.wsy.fyxw.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.service.SystemConfigService;

@Component("systemConfigUtil")
public class SystemConfigUtil {
	Logger logger = LoggerFactory.getLogger(getClass());
	private static String MAX_FAILED_TIMES = "maxFailedTimes";

	@Value("${defaultMaxFailedTimes}")
	private int defaultMaxFailedTimes;
	@Autowired
	private SystemConfigService systemConfigService;

	public int getMaxLoginFailedTimes() {
		int maxFailedTimes = defaultMaxFailedTimes;
		try {
			String maxFailedTimesStr = systemConfigService.getSystemConfigByKey(MAX_FAILED_TIMES);
			if (StringUtils.isNotBlank(maxFailedTimesStr)) {
				maxFailedTimes = Integer.valueOf(maxFailedTimesStr);
			}
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>getMaxLoginFailedTimes failed:{}", e.getMessage());
		}
		logger.info(">>>>>>>>>>>getMaxLoginFailedTimes :{}", maxFailedTimes);
		return maxFailedTimes;
	}
}
