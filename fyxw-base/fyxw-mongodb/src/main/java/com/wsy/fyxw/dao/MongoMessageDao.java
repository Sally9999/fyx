package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.MessageInfo;
import com.wsy.fyxw.domain.mongo.MongoMessageInfo;
import com.wsy.fyxw.query.MessageQuery;
import com.wsy.fyxw.repository.MongoMessageRepository;

@Component("mongoMessageDao")
public class MongoMessageDao extends MongoBaseDao<MessageInfo, MongoMessageInfo> implements MessageDao {

	@Autowired
	private MongoMessageRepository repository;

	@Override
	public ArrayList<MessageInfo> getPage(MessageQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<MessageInfo> getList(MessageQuery q) {
		return super.getList(q, repository);
	}

	@Override
	public long getCount(MessageQuery q) {
		return super.getCount(q, repository);
	}

	@Deprecated
	@Override
	public MessageInfo getByCode(String code) {
		return null;
	}

	@Override
	public int update(MessageInfo t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int add(MessageInfo t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(MessageInfo t) {
		return 0;
	}
}
