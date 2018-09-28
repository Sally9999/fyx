package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.query.MenuQuery;

@Mapper
public interface MenuDao extends BaseDao<Menu, MenuQuery>{

	public ArrayList<Menu> getMenuListByRoleAndGroup(MenuQuery query);
	
	public ArrayList<Menu> getAuthentication();
}
