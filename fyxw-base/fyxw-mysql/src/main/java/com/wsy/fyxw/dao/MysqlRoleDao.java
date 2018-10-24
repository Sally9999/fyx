package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.query.RoleQuery;
import com.wsy.fyxw.repository.MysqlRoleRepository;

@Component("mysqlRoleDao")
public class MysqlRoleDao implements RoleDao {
	@Autowired
	private MysqlRoleRepository repository;

	@Override
	public ArrayList<Role> getPage(RoleQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<Role> getList(RoleQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(RoleQuery q) {
		return repository.getCount(q);
	}

	@Override
	public Role getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(Role t) {
		return repository.update(t);
	}

	@Override
	public int add(Role t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(Role t) {
		return repository.changeStatusByCode(t);
	}

	@Override
	public List<Role> getRoleListByAccount(String acccount) {
		return repository.getRoleListByAccount(acccount);
	}
}
