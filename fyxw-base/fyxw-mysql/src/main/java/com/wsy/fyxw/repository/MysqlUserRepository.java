package com.wsy.fyxw.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.UserQuery;

@Mapper
public interface MysqlUserRepository extends MysqlBaseRepository<User, UserQuery> {

	public User getUserByAccount(@Param("account") String account, @Param("status") String status);

	public int changePwd(@Param("account") String account, @Param("newPwd") String newPwd,
			@Param("orgPwd") String orgPwd);
	
	public int changeStatusByAccount(User user);
	
	public List<Map<String, String>> statUserRegistTendency();
}
