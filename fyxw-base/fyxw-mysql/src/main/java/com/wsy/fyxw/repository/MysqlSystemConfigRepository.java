package com.wsy.fyxw.repository;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.query.SystemConfigQuery;

@Mapper
public interface MysqlSystemConfigRepository extends MysqlBaseRepository<SystemConfig, SystemConfigQuery>{

	public String getSystemConfigByKey(String key);
}
