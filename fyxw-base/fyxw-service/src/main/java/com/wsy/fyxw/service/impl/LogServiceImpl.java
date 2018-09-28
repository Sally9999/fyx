package com.wsy.fyxw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.LogDao;
import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;
import com.wsy.fyxw.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService{

	@Autowired 
	private LogDao logDao;
	
	@Override
	public int writeLog(LogInfo log) {
		return logDao.add(log);
	}

	@Override
	public Long getLogCount(LogInfoQuery query) {
		return logDao.getLogCount(query);
	}

}
