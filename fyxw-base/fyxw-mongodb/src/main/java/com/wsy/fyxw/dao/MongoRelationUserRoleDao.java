package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.RelationUserRole;
import com.wsy.fyxw.domain.mongo.MongoRelationUserRole;
import com.wsy.fyxw.query.RelationUserRoleQuery;
import com.wsy.fyxw.repository.MongoRelationUserRoleRepository;

@Component("mongoRelationUserRoleDao")
public class MongoRelationUserRoleDao extends MongoBaseDao<RelationUserRole, MongoRelationUserRole>
		implements RelationUserRoleDao {
	
	@Autowired
	private MongoRelationUserRoleRepository repository;

	@Override
	public ArrayList<RelationUserRole> getRelationUserRoleList(RelationUserRoleQuery query) {
		return super.getList(query, repository);
	}

	@Override
	public int insertBatch(ArrayList<RelationUserRole> list) {
		try {
			List<MongoRelationUserRole> insertList = new ArrayList<MongoRelationUserRole>();
			beanTransUtil.listTrans2(insertList, list, getClass());
			mongoTemplate.insert(insertList, MongoRelationUserRole.class);
			return list.size();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteBatch(ArrayList<RelationUserRole> list) {
		try {
			ArrayList<String> ids = new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(list)) {
				for (RelationUserRole relationUserRole : list) {
					ids.add(relationUserRole.getId());
				}
			}
			Query query = new Query(Criteria.where("_id").in(ids));
			return mongoTemplate.findAllAndRemove(query, MongoRelationUserRole.class).size();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int add(RelationUserRole relationUserRole) {
		return super.save(relationUserRole, repository);
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
