package com.wsy.fyxw.dao;

import java.util.List;
import java.util.Map;

import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.UserQuery;

public interface UserDao extends BaseDao<User, UserQuery> {

	public User getUserByAccount(String account, String status);

	public int changePwd(String account, String newPwd, String orgPwd);

	public int changeStatusByAccount(User user);

	public List<Map<String, String>> statUserRegistTendency();
}
