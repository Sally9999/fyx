package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.domain.mongo.MongoMenu;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.enums.EnumYesOrNo;
import com.wsy.fyxw.query.MenuQuery;
import com.wsy.fyxw.repository.MongoMenuRepository;

@Component("mongoMenuDao")
public class MongoMenuDao extends MongoBaseDao<Menu, MongoMenu> implements MenuDao {
	@Autowired
	private MongoMenuRepository repository;

	protected String getDefaultOrder() {
		return "sort";
	}

	protected Direction getDefaultASC() {
		return Direction.ASC;
	}

	@Override
	public ArrayList<Menu> getPage(MenuQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<Menu> getList(MenuQuery q) {
		String roleCode = q.getRoleCode();
		if (StringUtils.isNotBlank(roleCode)) {
			q.setRoleCode(null);
		}
		ArrayList<Menu> list = super.getList(q, repository);
		if (StringUtils.isNotBlank(roleCode) && CollectionUtils.isNotEmpty(list)) {
			setAuthority(roleCode, list);
		}
		return list;
	}

	private ArrayList<Menu> setAuthority(String roleCode, ArrayList<Menu> list) {
		for (Menu menu : list) {
			Query query = new Query(Criteria.where("roleCode").is(roleCode).and("menuCode").is(menu.getMenuCode()));
			long count = mongoTemplate.count(query, "relation_menu_role");
			if (count > 0) {
				menu.setHasAuthority(EnumYesOrNo.YES.getCode());
			} else {
				menu.setHasAuthority(EnumYesOrNo.NO.getCode());
			}
		}
		return list;
	}

	@Override
	public long getCount(MenuQuery q) {
		return super.getCount(q, repository);
	}

	@Override
	public Menu getByCode(String code) {
		return repository.findByMenuCode(code);
	}

	@Override
	public int update(Menu t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int add(Menu t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int deleteByCode(String code) {
		try {
			repository.deleteByMenuCode(code);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(Menu t) {
		return 0;
	}

	@Override
	public ArrayList<Menu> getMenuListByRoleAndGroup(MenuQuery query) {
		LookupOperation lookupOperation = LookupOperation.newLookup().from("relation_menu_role").// 关联表名
				localField("menuCode").// 关联字段
				foreignField("menuCode").// 主表关联字段对应的次表字段
				as("mr");// 查询结果集合名
		// 拼装具体查询信息
		// 次表
		Criteria ordercri = Criteria.where("mr").not().size(0);// 只查询有角色关联的菜单
		ordercri.and("mr.roleCode").in(query.getRoleCodeList());// 只查询在list中的角色
		AggregationOperation match = Aggregation.match(ordercri);
		// 主表
		Criteria maincri = Criteria.where("menuGroup").is(query.getMenuGroup());// 只查询group中的菜单
		maincri.and("status").is(query.getStatus());
		AggregationOperation match1 = Aggregation.match(maincri);
		Aggregation aggregation = Aggregation.newAggregation(match1, lookupOperation, match,
				Aggregation.sort(this.setSort(query)));
		List<Menu> results = mongoTemplate.aggregate(aggregation, "menu_info", Menu.class).getMappedResults();
		return new ArrayList<Menu>(results);
	}

	@Override
	public ArrayList<Menu> getAuthentication() {
		LookupOperation lookupOperation = LookupOperation.newLookup().from("relation_menu_role").// 关联表名
				localField("menuCode").// 关联字段
				foreignField("menuCode").// 主表关联字段对应的次表字段
				as("mr");// 查询结果集合名
		// 拼装具体查询信息
		// 次表
		Criteria ordercri = Criteria.where("mr").not().size(0);// 只查询有角色关联的菜单
		AggregationOperation match = Aggregation.match(ordercri);
		// 主表
		Criteria maincri = Criteria.where("status").is(EnumCommonStatus.NORMAL.getCode());// 只查询启用的菜单
		AggregationOperation match1 = Aggregation.match(maincri);
		MenuQuery query = new MenuQuery();
		query.setOrderBy("roleCode");
		Aggregation aggregation = Aggregation.newAggregation(match1, lookupOperation, match, Aggregation.unwind("mr"),
				Aggregation.project("menuCode","menuName","url","status").and("mr.roleCode").as("roleCode"), Aggregation.sort(this.setSort(query)));
		List<Menu> results = mongoTemplate.aggregate(aggregation, "menu_info", Menu.class).getMappedResults();
		return new ArrayList<Menu>(results);
	}
}
