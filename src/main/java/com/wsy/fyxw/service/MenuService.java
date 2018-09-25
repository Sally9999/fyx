package com.wsy.fyxw.service;

import java.util.ArrayList;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.dto.MenuGroupTreeDto;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.query.MenuQuery;

public interface MenuService {

	/**
	 * 通过角色获取菜单权限
	 * 
	 * @param roleCodeList
	 * @return
	 */
	public ArrayList<MenuGroup> getAuthorityByRoleList(ArrayList<String> roleCodeList);

	/**
	 * 分页查菜单组列表
	 * 
	 * @param query
	 * @return
	 */
	public MenuGroupQuery getMenuGroupPage(MenuGroupQuery query);

	/**
	 * 分页查菜单列表
	 * 
	 * @param query
	 * @return
	 */
	public MenuQuery getMenuPage(MenuQuery query);

	/**
	 * 根据groupCode获取菜单组详情
	 * 
	 * @param groupCode
	 * @return
	 */
	public MenuGroup getMenuGroupByCode(String groupCode);

	/**
	 * 根据menuCode获取菜单详情
	 * 
	 * @param menuCode
	 * @return
	 */
	public Menu getMenuByCode(String menuCode);
	
	/**
	 * 保存菜单组信息
	 * @param group
	 * @return
	 */
	public ResultInfo saveMenuGroup(MenuGroup group);
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @return
	 */
	public ResultInfo saveMenu(Menu menu);
	
	/**
	 * 删除菜单组
	 * @param group
	 * @return
	 */
	public ResultInfo deleteMenuGroup(MenuGroup group);
	
	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	public ResultInfo deleteMenu(Menu menu);
	
	/**
	 * 获取启用状态菜单信息并设置对应角色是否具有权限
	 * @param roleCode
	 * @return
	 */
	public ArrayList<MenuGroupTreeDto> getMenuGroupWithRole(String roleCode);
	
	public ArrayList<Menu> getAuthentication();
}
