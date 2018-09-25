package com.wsy.fyxw.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.UserQuery;

public interface UserService extends UserDetailsService {

	/**
	 * 根据id修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @return
	 */
	public ResultInfo createUser(User user);

	/**
	 * 更新密码
	 * 
	 * @param user
	 * @param newPwd
	 * @param orgCheck
	 * @return
	 */
	public ResultInfo changePwd(User user, String newPwd, Boolean orgCheck);

	/**
	 * 分页
	 * 
	 * @param query
	 * @return
	 */
	public UserQuery getUserPage(UserQuery query);

	/**
	 * 状态变更
	 * 
	 * @param user
	 * @return
	 */
	public ResultInfo changeStatus(User user);

	/**
	 * 角色设置
	 * 
	 * @param roleCodes
	 * @param account
	 * @param operator
	 * @return
	 */
	public ResultInfo saveRoles(ArrayList<String> roleCodes, String account, String operator);

	/**
	 * 根据account查找用户信息
	 * 
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account);
}
