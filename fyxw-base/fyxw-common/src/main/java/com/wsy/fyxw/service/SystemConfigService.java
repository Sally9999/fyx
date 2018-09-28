package com.wsy.fyxw.service;

import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.query.SystemConfigQuery;

public interface SystemConfigService {

	/**
	 * 通过key获取系统配置信息
	 * 
	 * @param key
	 * @return
	 */
	public String getSystemConfigByKey(String key);

	public SystemConfigQuery getConfigPage(SystemConfigQuery query);

	public ResultInfo save(SystemConfig config);

	public ResultInfo changeStatus(SystemConfig config);
}
