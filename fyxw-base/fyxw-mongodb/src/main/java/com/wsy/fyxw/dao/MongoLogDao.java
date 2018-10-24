package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.domain.mongo.MongoLogInfo;
import com.wsy.fyxw.query.LogInfoQuery;
import com.wsy.fyxw.repository.MongoLogRepository;

@Component("mongoLogDao")
public class MongoLogDao extends MongoBaseDao<LogInfo,MongoLogInfo> implements LogDao {

	@Autowired
	private MongoLogRepository repository;

	@Override
	public ArrayList<LogInfo> getPage(LogInfoQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<LogInfo> getList(LogInfoQuery q) {
		return super.getList(q, repository);
	}

	@Override
	public long getCount(LogInfoQuery q) {
		return super.getCount(q, repository);
	}
	
	@Deprecated
	@Override
	public LogInfo getByCode(String code) {
		return null;
	}

	@Override
	public int update(LogInfo t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t,repository);
	}

	@Override
	public int add(LogInfo t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t,repository);
	}
	
	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(LogInfo t) {
		return 0;
	}
}
