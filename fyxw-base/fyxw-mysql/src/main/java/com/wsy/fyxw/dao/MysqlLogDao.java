package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.query.LogInfoQuery;
import com.wsy.fyxw.repository.MysqlLogRepository;

@Component("mysqlLogDao")
public class MysqlLogDao implements LogDao {

	@Autowired
	private MysqlLogRepository repository;
	
	@Override
	public ArrayList<LogInfo> getPage(LogInfoQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<LogInfo> getList(LogInfoQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(LogInfoQuery q) {
		return repository.getCount(q);
	}

	@Override
	public LogInfo getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(LogInfo t) {
		return repository.update(t);
	}

	@Override
	public int add(LogInfo t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(LogInfo t) {
		return repository.changeStatusByCode(t);
	}
}
