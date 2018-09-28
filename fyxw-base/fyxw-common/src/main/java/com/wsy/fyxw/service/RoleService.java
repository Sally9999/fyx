package com.wsy.fyxw.service;

import java.util.ArrayList;

import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.dto.RoleTreeDto;
import com.wsy.fyxw.query.RoleQuery;

public interface RoleService {

	/**
	 * 分页
	 * 
	 * @param query
	 * @return
	 */
	public RoleQuery getRolePage(RoleQuery query);

	/**
	 * 根据code获取角色信息
	 * 
	 * @param roleCode
	 * @return
	 */
	public Role getRoleByCode(String roleCode);

	/**
	 * 新增or修改
	 * 
	 * @param role
	 * @return
	 */
	public ResultInfo saveRole(Role role);

	/**
	 * 删除
	 * 
	 * @param role
	 * @return
	 */
	public ResultInfo deleteRole(Role role);

	/**
	 * 状态变更
	 * 
	 * @param role
	 * @return
	 */
	public ResultInfo changeStatus(Role role);

	/**
	 * 角色授权
	 * 
	 * @param menuCodes
	 * @param roleCode
	 * @param operator
	 * @return
	 */
	public ResultInfo saveAuthority(ArrayList<String> menuCodes, String roleCode, String operator);

	/**
	 * 根据account查询所有启用角色及是否具有权限
	 * 
	 * @param account
	 * @return
	 */
	public ArrayList<RoleTreeDto> getRoleListWithAccount(String account);
}
