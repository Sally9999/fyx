package com.wsy.fyxw.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.query.SystemConfigQuery;

@Mapper
public interface SystemConfigDao extends BaseDao<SystemConfig, SystemConfigQuery>{

	public String getSystemConfigByKey(String key);
}
