package com.wsy.fyxw.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.UserQuery;

@Mapper
public interface UserDao extends BaseDao<User, UserQuery> {

	public User getUserByAccount(@Param("account") String account, @Param("status") String status);

	public int changePwd(@Param("account") String account, @Param("newPwd") String newPwd,
			@Param("orgPwd") String orgPwd);
	
	public int changeStatusByAccount(User user);
}
