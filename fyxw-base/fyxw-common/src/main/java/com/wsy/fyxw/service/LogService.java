package com.wsy.fyxw.service;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;

public interface LogService {

	/**
	 * 写日志
	 * 
	 * @param log
	 * @return
	 */
	public int writeLog(LogInfo log);

	/**
	 * 计算日志数量
	 * 
	 * @param query
	 * @return
	 */
	public Long getLogCount(LogInfoQuery query);
}
