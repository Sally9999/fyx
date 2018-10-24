package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.RelationUserRole;
import com.wsy.fyxw.query.RelationUserRoleQuery;
import com.wsy.fyxw.repository.MysqlRelationUserRoleRepository;

@Component("mysqlRelationUserRoleDao")
public class MysqlRelationUserRoleDao implements RelationUserRoleDao{
	@Autowired
	private MysqlRelationUserRoleRepository repository;

	@Override
	public ArrayList<RelationUserRole> getRelationUserRoleList(RelationUserRoleQuery query) {
		return repository.getRelationUserRoleList(query);
	}

	@Override
	public int insertBatch(ArrayList<RelationUserRole> list) {
		return repository.insertBatch(list);
	}

	@Override
	public int deleteBatch(ArrayList<RelationUserRole> list) {
		return repository.deleteBatch(list);
	}

	@Override
	public int add(RelationUserRole relationUserRole) {
		return repository.add(relationUserRole);
	}

	@Deprecated
	@Override
	public ArrayList<RelationUserRole> getPage(RelationUserRoleQuery q) {
		return null;
	}

	@Deprecated
	@Override
	public ArrayList<RelationUserRole> getList(RelationUserRoleQuery q) {
		return null;
	}

	@Deprecated
	@Override
	public long getCount(RelationUserRoleQuery q) {
		return 0;
	}

	@Deprecated
	@Override
	public RelationUserRole getByCode(String code) {
		return null;
	}

	@Deprecated
	@Override
	public int update(RelationUserRole t) {
		return 0;
	}

	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(RelationUserRole t) {
		return 0;
	}
}
