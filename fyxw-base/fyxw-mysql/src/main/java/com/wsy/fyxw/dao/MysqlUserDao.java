package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.UserQuery;
import com.wsy.fyxw.repository.MysqlUserRepository;

@Component("mysqlUserDao")
public class MysqlUserDao implements UserDao {
	@Autowired
	private MysqlUserRepository repository;

	@Override
	public ArrayList<User> getPage(UserQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<User> getList(UserQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(UserQuery q) {
		return repository.getCount(q);
	}

	@Override
	public User getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(User t) {
		return repository.update(t);
	}

	@Override
	public int add(User t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(User t) {
		return repository.changeStatusByCode(t);
	}

	@Override
	public User getUserByAccount(String account, String status) {
		return repository.getUserByAccount(account, status);
	}

	@Override
	public int changePwd(String account, String newPwd, String orgPwd) {
		return repository.changePwd(account, newPwd, orgPwd);
	}

	@Override
	public int changeStatusByAccount(User user) {
		return repository.changeStatusByAccount(user);
	}

	@Override
	public List<Map<String, String>> statUserRegistTendency() {
		return repository.statUserRegistTendency();
	}
}
