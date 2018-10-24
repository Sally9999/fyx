package com.wsy.fyxw.dao;

import java.util.ArrayList;

import com.wsy.fyxw.domain.RelationMenuRole;
import com.wsy.fyxw.query.RelationMenuRoleQuery;

public interface RelationMenuRoleDao extends BaseDao<RelationMenuRole, RelationMenuRoleQuery> {

	/**
	 * 获取关系列表
	 * 
	 * @param query
	 * @return
	 */
	public ArrayList<RelationMenuRole> getRelationMenuRoleList(RelationMenuRoleQuery query);

	/**
	 * 批量新增
	 * 
	 * @param list
	 * @return
	 */
	public int insertBatch(ArrayList<RelationMenuRole> list);

	/**
	 * 批量删除
	 * 
	 * @param list
	 * @return
	 */
	public int deleteBatch(ArrayList<RelationMenuRole> list);
}
