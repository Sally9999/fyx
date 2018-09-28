package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.query.MenuGroupQuery;

@Mapper
public interface MenuGroupDao extends BaseDao<MenuGroup, MenuGroupQuery>{

	public ArrayList<MenuGroup> getMenuGroupList(String status);
}
