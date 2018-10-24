package com.wsy.fyxw.repository;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;

@Mapper
public interface MysqlLogRepository extends MysqlBaseRepository<LogInfo, LogInfoQuery> {

}
