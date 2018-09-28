package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.wsy.fyxw.domain.RelationUserRole;
import com.wsy.fyxw.query.RelationUserRoleQuery;

@Mapper
public interface RelationUserRoleDao {
	/**
	 * 获取关系列表
	 * 
	 * @param query
	 * @return
	 */
	public ArrayList<RelationUserRole> getRelationUserRoleList(RelationUserRoleQuery query);

	/**
	 * 批量新增
	 * 
	 * @param list
	 * @return
	 */
	public int insertBatch(ArrayList<RelationUserRole> list);

	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	public int deleteBatch(ArrayList<RelationUserRole> list);

	/**
	 * 新增
	 * 
	 * @param relationUserRole
	 * @return
	 */
	public int add(RelationUserRole relationUserRole);
}
