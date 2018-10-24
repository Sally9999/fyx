package com.wsy.fyxw.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.LogDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;
import com.wsy.fyxw.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

	private LogDao logDao;

	@Autowired
	public LogServiceImpl(DaoFactory daoFactory, @Value("${datasource.type}") String DATA_SOURCE_TYPE) {
		super();
		this.logDao = (LogDao) daoFactory.getDao(DATA_SOURCE_TYPE + "LogDao");
	}

	@Override
	public int writeLog(LogInfo log) {
		return logDao.add(log);
	}

	@Override
	public Long getLogCount(LogInfoQuery query) {
		return logDao.getCount(query);
	}

	@Override
	public LogInfoQuery getLogPage(LogInfoQuery query) {
		long count = logDao.getCount(query);
		ArrayList<LogInfo> list = logDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

}
