package com.wsy.fyxw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;

@Mapper
public interface LogDao extends BaseDao<LogInfo, LogInfoQuery> {

	public List<LogInfo> getLogList(LogInfoQuery query);

	public Long getLogCount(LogInfoQuery query);
}
