package com.wsy.fyxw.dao;

import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.query.SystemConfigQuery;

public interface SystemConfigDao extends BaseDao<SystemConfig, SystemConfigQuery>{

	public String getSystemConfigByKey(String key);
}
