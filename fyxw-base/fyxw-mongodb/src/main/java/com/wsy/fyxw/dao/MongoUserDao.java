package com.wsy.fyxw.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.domain.mongo.MongoUser;
import com.wsy.fyxw.query.UserQuery;
import com.wsy.fyxw.repository.MongoUserRepository;

@Component("mongoUserDao")
public class MongoUserDao extends MongoBaseDao<User, MongoUser> implements UserDao {
	@Autowired
	private MongoUserRepository repository;

	@Override
	public ArrayList<User> getPage(UserQuery q) {
		return super.getPage(q, repository);
	}

	@Override
	public ArrayList<User> getList(UserQuery q) {
		return super.getList(q, repository);
	}

	@Override
	public long getCount(UserQuery q) {
		return super.getCount(q, repository);
	}

	@Deprecated
	@Override
	public User getByCode(String code) {
		return null;
	}

	@Override
	public int update(User t) {
		Date date = new Date();
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Override
	public int add(User t) {
		Date date = new Date();
		t.setGmtCreate(date);
		t.setGmtModify(date);
		return super.save(t, repository);
	}

	@Deprecated
	@Override
	public int deleteByCode(String code) {
		return 0;
	}

	@Deprecated
	@Override
	public int changeStatusByCode(User t) {
		return 0;
	}

	@Override
	public User getUserByAccount(String account, String status) {
		User user = null;
		try {
			Criteria cri = Criteria.where("account").is(account);
			if (StringUtils.isNotBlank(status)) {
				cri.and("status").is(status);
			}
			Query query = new Query(cri);
			MongoUser mongoUser = mongoTemplate.findOne(query, MongoUser.class);
			if (null != mongoUser) {
				user = new User();
				BeanUtils.copyProperties(user, mongoUser);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return user;
	}

	@Override
	public int changePwd(String account, String newPwd, String orgPwd) {
		Criteria cri = Criteria.where("account").is(account);
		if (StringUtils.isNotBlank(orgPwd)) {
			cri.and("pwd").is(orgPwd);
		}
		Query query = new Query(cri);
		// 拼装修改数据
		Update update = new Update();
		update.set("gmtModify", new Date());
		update.set("pwd", newPwd);
		UpdateResult result = mongoTemplate.updateFirst(query, update, MongoUser.class);
		return (int) result.getModifiedCount();
	}

	@Override
	public int changeStatusByAccount(User user) {
		Query query = new Query(Criteria.where("account").is(user.getAccount()));
		// 拼装修改数据
		Update update = new Update();
		update.set("gmtModify", new Date());
		update.set("status", user.getStatus());
		UpdateResult result = mongoTemplate.updateFirst(query, update, MongoUser.class);
		return (int) result.getModifiedCount();
	}

	@Override
	public List<Map<String, String>> statUserRegistTendency() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			int days = 6;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String s = format.format(new Date());
			Date date = format.parse(s);
			Criteria cri = Criteria.where("gmtCreate").gt(DateUtils.addDays(date, -days));
			AggregationOperation match = Aggregation.match(cri);
			Aggregation aggregation = Aggregation.newAggregation(
					match, Aggregation.project()
							.andExpression("{$dateToString:{format:\"%Y-%m-%d\",date:\"$gmtCreate\"}}").as("date"),
					Aggregation.group("date").count().as("count"));
			AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "user_info", Document.class);
			for (; days >= 0; days--) {
				Map<String, String> statMap = new HashMap<String, String>();
				String dateStr = format.format(DateUtils.addDays(date, -days));
				String count = "0";
				for (Iterator<Document> iterator = results.iterator(); iterator.hasNext();) {
					Document obj = iterator.next();
					if (StringUtils.equals(obj.getString("_id"), dateStr)) {
						count = obj.getInteger("count").toString();
						break;
					}
				}
				statMap.put("date", dateStr);
				statMap.put("count", count);
				list.add(statMap);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return list;
	}
}
