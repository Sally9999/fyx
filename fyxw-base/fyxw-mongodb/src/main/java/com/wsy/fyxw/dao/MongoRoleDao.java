package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.domain.mongo.MongoRole;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.enums.EnumYesOrNo;
import com.wsy.fyxw.query.RoleQuery;
import com.wsy.fyxw.repository.MongoRoleRepository;

@Component("mongoRoleDao")
public class MongoRoleDao extends MongoBaseDao<Role, MongoRole> implements RoleDao {
	@Autowired
	private MongoRoleRepository repository;

	@Override
	public ArrayList<Role> getPage(RoleQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<Role> getList(RoleQuery q) {
		String account = q.getAccount();
		if (StringUtils.isNotBlank(account)) {
			q.setAccount(null);
		}
		ArrayList<Role> list = super.getList(q, repository);
		if (StringUtils.isNotBlank(account) && CollectionUtils.isNotEmpty(list)) {
			setAuthority(account, list);
		}
		return list;
	}

	private ArrayList<Role> setAuthority(String account, ArrayList<Role> list) {
		for (Role role : list) {
			Query query = new Query(
					Criteria.where("account").is(account).and("roleCode").is(role.getRoleCode()));
			long count = mongoTemplate.count(query, "relation_user_role");
			if (count > 0) {
				role.setHasAuthority(EnumYesOrNo.YES.getCode());
			} else {
				role.setHasAuthority(EnumYesOrNo.NO.getCode());
			}
		}
		return list;
	}

	@Override
	public long getCount(RoleQuery q) {
		return super.getCount(q, repository);
	}

	@Override
	public Role getByCode(String code) {
		return repository.findByRoleCode(code);
	}

	@Override
	public int update(Role t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int add(Role t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByRoleCode(code);
	}

	@Override
	public int changeStatusByCode(Role t) {
		Query query = new Query(Criteria.where("roleCode").is(t.getRoleCode()));
		// 拼装修改数据
		Update update = new Update();
		update.set("gmtModify", new Date());
		update.set("status", t.getStatus());
		UpdateResult result = mongoTemplate.updateFirst(query, update, Role.class);
		return (int) result.getModifiedCount();
	}

	@Override
	public List<Role> getRoleListByAccount(String acccount) {
		LookupOperation lookupOperation = LookupOperation.newLookup().from("relation_user_role").// 关联表名
				localField("roleCode").// 关联字段
				foreignField("roleCode").// 主表关联字段对应的次表字段
				as("ur");// 查询结果集合名
		// 拼装具体查询信息
		// 次表
		Criteria ordercri = Criteria.where("ur").not().size(0).and("ur.account").is(acccount);
		AggregationOperation match = Aggregation.match(ordercri);
		// 主表
		Criteria maincri = Criteria.where("status").is(EnumCommonStatus.NORMAL.getCode());// 只查询启用的菜单
		AggregationOperation match1 = Aggregation.match(maincri);
		Aggregation aggregation = Aggregation.newAggregation(match1, lookupOperation, match);
		List<Role> results = mongoTemplate.aggregate(aggregation, "role_info", Role.class).getMappedResults();
		return new ArrayList<Role>(results);
	}
}
