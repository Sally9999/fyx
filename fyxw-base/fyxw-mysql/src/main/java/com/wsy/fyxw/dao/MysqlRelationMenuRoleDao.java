package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.RelationMenuRole;
import com.wsy.fyxw.query.RelationMenuRoleQuery;
import com.wsy.fyxw.repository.MysqlRelationMenuRoleRepository;

@Component("mysqlRelationMenuRoleDao")
public class MysqlRelationMenuRoleDao implements RelationMenuRoleDao {
	@Autowired
	private MysqlRelationMenuRoleRepository repository;

	@Override
	public ArrayList<RelationMenuRole> getRelationMenuRoleList(RelationMenuRoleQuery query) {
		return repository.getRelationMenuRoleList(query);
	}

	@Override
	public int insertBatch(ArrayList<RelationMenuRole> list) {
		return repository.insertBatch(list);
	}

	@Override
	public int deleteBatch(ArrayList<RelationMenuRole> list) {
		return repository.deleteBatch(list);
	}

	@Deprecated
	@Override
	public ArrayList<RelationMenuRole> getPage(RelationMenuRoleQuery q) {
		return null;
	}

	@Deprecated
	@Override
	public ArrayList<RelationMenuRole> getList(RelationMenuRoleQuery q) {
		return null;
	}

	@Deprecated
	@Override
	public long getCount(RelationMenuRoleQuery q) {
		return 0;
	}

	@Deprecated
	@Override
	public RelationMenuRole getByCode(String code) {
		return null;
	}

	@Deprecated
	@Override
	public int update(RelationMenuRole t) {
		return 0;
	}

	@Deprecated
	@Override
	public int add(RelationMenuRole t) {
		return 0;
	}

	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(RelationMenuRole t) {
		return 0;
	}
}
