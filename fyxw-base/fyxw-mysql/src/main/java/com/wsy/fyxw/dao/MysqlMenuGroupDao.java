package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.repository.MysqlMenuGroupRepository;

@Component("mysqlMenuGroupDao")
public class MysqlMenuGroupDao implements MenuGroupDao {
	@Autowired
	private MysqlMenuGroupRepository repository;

	@Override
	public ArrayList<MenuGroup> getPage(MenuGroupQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<MenuGroup> getList(MenuGroupQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(MenuGroupQuery q) {
		return repository.getCount(q);
	}

	@Override
	public MenuGroup getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(MenuGroup t) {
		return repository.update(t);
	}

	@Override
	public int add(MenuGroup t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(MenuGroup t) {
		return repository.changeStatusByCode(t);
	}
}
