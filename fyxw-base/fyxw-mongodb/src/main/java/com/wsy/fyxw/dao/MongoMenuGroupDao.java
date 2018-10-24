package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.mongo.MongoMenuGroup;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.repository.MongoMenuGroupRepository;

@Component("mongoMenuGroupDao")
public class MongoMenuGroupDao extends MongoBaseDao<MenuGroup, MongoMenuGroup> implements MenuGroupDao {
	@Autowired
	private MongoMenuGroupRepository repository;

	protected String getDefaultOrder() {
		return "sort";
	}
	
	protected Direction getDefaultASC() {
		return Direction.ASC;
	}
	
	@Override
	public ArrayList<MenuGroup> getPage(MenuGroupQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<MenuGroup> getList(MenuGroupQuery q) {
		return super.getList(q, repository);
	}

	@Override
	public long getCount(MenuGroupQuery q) {
		return super.getCount(q, repository);
	}

	@Override
	public MenuGroup getByCode(String code) {
		return repository.findByGroupCode(code);
	}

	@Override
	public int update(MenuGroup t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int add(MenuGroup t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int deleteByCode(String code) {
		try {
			repository.deleteByGroupCode(code);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(MenuGroup t) {
		return 0;
	}
}
