package com.wsy.fyxw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.RelationUserRoleDao;
import com.wsy.fyxw.dao.RoleDao;
import com.wsy.fyxw.dao.UserDao;
import com.wsy.fyxw.domain.RelationUserRole;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumDefaultRoleType;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumUserResult;
import com.wsy.fyxw.enums.EnumUserStatus;
import com.wsy.fyxw.query.RelationUserRoleQuery;
import com.wsy.fyxw.query.UserQuery;
import com.wsy.fyxw.service.UserService;
import com.wsy.fyxw.util.LogUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private LogUtil logUtil;
	@Autowired
	private RelationUserRoleDao relationUserRoleDao;

	/**
	 * security登录验证调用
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getUserByAccount(username, EnumUserStatus.NORMAL.getCode());
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		// 获取角色信息
		List<Role> roles = roleDao.getRoleListByAccount(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (CollectionUtils.isNotEmpty(roles)) {
			for (Role role : roles) {
				// 权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
			}
		}
		user.setAuthorities(authorities);
		return user;
	}

	/**
	 * 根据id更新用户信息
	 */
	@Override
	public int updateUser(User user) {
		if (null == user || null == user.getId() || 0l == user.getId()) {
			return 0;
		}
		return userDao.update(user);
	}

	/**
	 * 新增用户
	 */
	@Override
	public ResultInfo createUser(User user) {

		// 检查对象信息是否完全
		ResultInfo result = this.checkCreate(user);
		if (StringUtils.equals(result.getCode(), EnumUserResult.SUCCESS.getCode())) {
			// 设置默认项
			this.setDefaultInfo(user);
			// 新增用户
			try {
				int n = userDao.add(user);
				// 创建失败
				if (n <= 0) {
					this.setResultInfo(EnumUserResult.FAILED, result);
				} else {
					// 设置默认角色
					RelationUserRole relation = new RelationUserRole();
					relation.setAccount(user.getAccount());
					relation.setRoleCode(EnumDefaultRoleType.COMMON.getCode());
					relationUserRoleDao.add(relation);
				}
			} catch (Exception e) {
				logger.error(">>>>>>>>>>>createUser failed:{}", e.getMessage());
				this.setResultInfo(EnumUserResult.FAILED, result);
			}
		}
		return result;
	}

	/**
	 * 检测用户信息是否完备
	 * 
	 * @param user
	 * @return
	 */
	private ResultInfo checkCreate(User user) {
		if (null == user || StringUtils.isBlank(user.getAccount())) {
			return this.setResultInfo(EnumUserResult.ACCOUNT_NULL, null);
		}
		String pwd = user.getPwd();
		// 密码为空
		if (StringUtils.isBlank(pwd)) {
			return this.setResultInfo(EnumUserResult.PWD_NULL, null);
		}
		// 用户名是否已存在
		if (null != userDao.getUserByAccount(user.getAccount(), null)) {
			return this.setResultInfo(EnumUserResult.USER_EXIST, null);
		}
		return this.setResultInfo(EnumUserResult.SUCCESS, null);
	}

	/**
	 * 补全用户信息
	 * 
	 * @param user
	 */
	private void setDefaultInfo(User user) {
		user.setLoginFailedTimes(0);
		user.setStatus(EnumUserStatus.NORMAL.getCode());
	}

	/**
	 * 设置返回结果
	 * 
	 * @param enumUserResult
	 * @param result
	 * @return
	 */
	private ResultInfo setResultInfo(EnumUserResult enumUserResult, ResultInfo result) {
		if (null == result) {
			result = new ResultInfo(enumUserResult.getCode(), enumUserResult.getValue());
		} else {
			result.setCode(enumUserResult.getCode());
			result.setValue(enumUserResult.getValue());
		}
		return result;
	}

	@Override
	public ResultInfo changePwd(User user, String newPwd, Boolean orgCheck) {
		if (StringUtils.equals(newPwd, user.getPwd())) {
			return new ResultInfo(EnumUserResult.NEW_PWD_EQ_ORG.getCode(), EnumUserResult.NEW_PWD_EQ_ORG.getValue());
		}
		String orgPwd = "";
		if (orgCheck) {
			orgPwd = user.getPwd();
		}
		if (userDao.changePwd(user.getAccount(), newPwd, orgPwd) > 0) {
			logUtil.writeLogCommon(user.getAccount(), EnumLogType.PASSWORD_CHANGED.getCode(), user.getAccount(),
					EnumCommonResult.SUCCESS);
			return new ResultInfo(EnumUserResult.SUCCESS.getCode(), EnumUserResult.SUCCESS.getValue());
		}
		return new ResultInfo(EnumUserResult.FAILED.getCode(), EnumUserResult.FAILED.getValue());
	}

	@Override
	public UserQuery getUserPage(UserQuery query) {
		int count = userDao.getCount(query);
		ArrayList<User> list = userDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

	@Override
	public ResultInfo changeStatus(User user) {
		ResultInfo result = this.setResultInfo(EnumUserResult.FAILED, null);
		if (StringUtils.isBlank(user.getAccount())) {
			return this.setResultInfo(EnumUserResult.ACCOUNT_NULL, result);
		}
		if (StringUtils.isBlank(user.getStatus())) {
			return this.setResultInfo(EnumUserResult.STATUS_NULL, result);
		}
		if (userDao.changeStatusByAccount(user) > 0) {
			this.setResultInfo(EnumUserResult.SUCCESS, result);
			String message = EnumLogType.USER_STATUS_CHANGE.getValue() + ":" + user.getAccount() + "["
					+ user.getStatus() + "]";
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(),
					EnumLogType.USER_STATUS_CHANGE.getCode(), user.getOperator(), message);
		}
		return result;
	}

	@Override
	public ResultInfo saveRoles(ArrayList<String> roleCodes, String account, String operator) {
		ResultInfo result = this.setResultInfo(EnumUserResult.FAILED, null);
		try {
			if (StringUtils.isBlank(account)) {
				return this.setResultInfo(EnumUserResult.ACCOUNT_NULL, result);
			}
			ArrayList<RelationUserRole> insertList = new ArrayList<RelationUserRole>();
			ArrayList<RelationUserRole> deleteList = new ArrayList<RelationUserRole>();
			// 查询用户已有角色
			RelationUserRoleQuery query = new RelationUserRoleQuery();
			query.setAccount(account);
			ArrayList<RelationUserRole> list = relationUserRoleDao.getRelationUserRoleList(query);
			if (CollectionUtils.isEmpty(roleCodes)) {
				// 删除所有权限
				deleteList = list;
			} else {
				if (CollectionUtils.isNotEmpty(list)) {
					for (RelationUserRole relationUserRole : list) {
						// roleCodes 中没有则删除对应角色关联
						if (!roleCodes.contains(relationUserRole.getRoleCode())) {
							deleteList.add(relationUserRole);
						}
					}
					for (String roleCode : roleCodes) {
						boolean flag = false;// 记录是否存在
						for (RelationUserRole relationUserRole : list) {
							if (StringUtils.equals(roleCode, relationUserRole.getRoleCode())) {
								flag = true;
								break;
							}
						}
						if (!flag) {
							// 不存在则新增
							RelationUserRole relation = new RelationUserRole();
							relation.setAccount(account);
							relation.setRoleCode(roleCode);
							insertList.add(relation);
						}
					}
				} else {
					// 全部新增
					for (String roleCode : roleCodes) {
						RelationUserRole relation = new RelationUserRole();
						relation.setAccount(account);
						relation.setRoleCode(roleCode);
						insertList.add(relation);
					}
				}
			}
			if (CollectionUtils.isNotEmpty(deleteList)) {
				relationUserRoleDao.deleteBatch(deleteList);
			}
			if (CollectionUtils.isNotEmpty(insertList)) {
				relationUserRoleDao.insertBatch(insertList);
			}
			// 记录日志
			String message = account + EnumLogType.USER_ROLE_CHANGE.getValue() + ":" + roleCodes.toString();
			logUtil.writeSuccessLogCommon(EnumDefaultRoleType.SYSTEM.getCode(), EnumLogType.USER_ROLE_CHANGE.getCode(),
					operator, message);
			this.setResultInfo(EnumUserResult.SUCCESS, result);
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>saveRoles failed:{}", e.getMessage());
		}
		return result;
	}

	@Override
	public User getUserByAccount(String account) {
		if (StringUtils.isNotBlank(account)) {
			return userDao.getUserByAccount(account, null);
		}
		logger.error("account:{} is blank", account);
		return null;
	}
}
