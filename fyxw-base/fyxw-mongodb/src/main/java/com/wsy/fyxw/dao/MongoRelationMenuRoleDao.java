package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.RelationMenuRole;
import com.wsy.fyxw.domain.mongo.MongoRelationMenuRole;
import com.wsy.fyxw.query.RelationMenuRoleQuery;
import com.wsy.fyxw.repository.MongoRelationMenuRoleRepository;

@Component("mongoRelationMenuRoleDao")
public class MongoRelationMenuRoleDao extends MongoBaseDao<RelationMenuRole, MongoRelationMenuRole>
		implements RelationMenuRoleDao {
	@Autowired
	private MongoRelationMenuRoleRepository repository;

	@Override
	public ArrayList<RelationMenuRole> getRelationMenuRoleList(RelationMenuRoleQuery query) {
		return super.getList(query, repository);
	}

	@Override
	public int insertBatch(ArrayList<RelationMenuRole> list) {
		try {
			List<MongoRelationMenuRole> insertList = new ArrayList<MongoRelationMenuRole>();
			beanTransUtil.listTrans2(insertList, list, getClass());
			mongoTemplate.insert(insertList, MongoRelationMenuRole.class);
			return list.size();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public int deleteBatch(ArrayList<RelationMenuRole> list) {
		try {
			ArrayList<String> ids = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(list)) {
				for (RelationMenuRole relationMenuRole : list) {
					ids.add(relationMenuRole.getId());
				}
			}
			Query query = new Query(Criteria.where("_id").in(ids));
			return mongoTemplate.findAllAndRemove(query, MongoRelationMenuRole.class).size();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
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
