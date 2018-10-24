package com.wsy.fyxw.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mongodb.client.result.UpdateResult;
import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.domain.mongo.MongoSystemConfig;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.query.SystemConfigQuery;
import com.wsy.fyxw.repository.MongoSystemConfigRepository;

@Component("mongoSystemConfigDao")
public class MongoSystemConfigDao extends MongoBaseDao<SystemConfig, MongoSystemConfig> implements SystemConfigDao {
	@Autowired
	private MongoSystemConfigRepository repository;

	@Override
	public ArrayList<SystemConfig> getPage(SystemConfigQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<SystemConfig> getList(SystemConfigQuery q) {
		return super.getList(q, repository);
	}

	@Override
	public long getCount(SystemConfigQuery q) {
		return super.getCount(q, repository);
	}

	@Override
	public SystemConfig getByCode(String code) {
		return repository.findByAttrKey(code);
	}

	@Override
	public int update(SystemConfig t) {
		Query query = new Query(Criteria.where("attrKey").is(t.getAttrKey()));
		// 拼装修改数据
		Update update = new Update();
		update.set("gmtModify", new Date());
		update.set("attrValue", t.getAttrValue());
		UpdateResult result = mongoTemplate.updateFirst(query, update, MongoSystemConfig.class);
		return (int) result.getModifiedCount();
	}

	@Deprecated
	@Override
	public int add(SystemConfig t) {
		return 0;
	}

	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Override
	public int changeStatusByCode(SystemConfig t) {
		Query query = new Query(
				Criteria.where("attrKey").is(t.getAttrKey()).and("required").is(EnumCommonStatus.NORMAL.getCode()));
		// 拼装修改数据
		Update update = new Update();
		update.set("gmtModify", new Date());
		update.set("status", t.getStatus());
		UpdateResult result = mongoTemplate.updateFirst(query, update, MongoSystemConfig.class);
		return (int) result.getModifiedCount();
	}

	@Override
	public String getSystemConfigByKey(String key) {
		AggregationOperation match = Aggregation
				.match(Criteria.where("attrKey").is(key).and("status").is(EnumCommonStatus.NORMAL.getCode()));
		Aggregation aggregation = Aggregation.newAggregation(match, Aggregation.project("attrValue").andExclude("_id"));
		AggregationResults<Document> result = mongoTemplate.aggregate(aggregation, "system_config", Document.class);
		Iterator<Document> iterator = result.iterator();
		if (iterator.hasNext()) {
			Document obj = iterator.next();
			return obj.getString("attrValue");
		}
		return null;
	}
}
