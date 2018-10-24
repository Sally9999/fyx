package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.query.MenuQuery;
import com.wsy.fyxw.repository.MysqlMenuRepository;

@Component("mysqlMenuDao")
public class MysqlMenuDao implements MenuDao {
	@Autowired
	private MysqlMenuRepository repository;

	@Override
	public ArrayList<Menu> getPage(MenuQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<Menu> getList(MenuQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(MenuQuery q) {
		return repository.getCount(q);
	}

	@Override
	public Menu getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(Menu t) {
		return repository.update(t);
	}

	@Override
	public int add(Menu t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(Menu t) {
		return  repository.changeStatusByCode(t);
	}

	@Override
	public ArrayList<Menu> getMenuListByRoleAndGroup(MenuQuery query) {
		return repository.getMenuListByRoleAndGroup(query);
	}

	@Override
	public ArrayList<Menu> getAuthentication() {
		return repository.getAuthentication();
	}
}
