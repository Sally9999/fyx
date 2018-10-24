package com.wsy.fyxw.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.query.BaseQueryPage;
import com.wsy.fyxw.util.BeanTransUtil;

public class MongoBaseDao<T, M> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String DEFAULT_ORDER = "gmtModify";

	protected Direction DEFAULT_ASC = Direction.DESC;

	@Autowired
	protected BeanTransUtil<T, M> beanTransUtil;
	
	@Autowired
	protected MongoTemplate mongoTemplate;

	protected String getDefaultOrder() {
		return DEFAULT_ORDER;
	}
	
	protected Direction getDefaultASC() {
		return DEFAULT_ASC;
	}
	
	protected Sort setSort(BaseQueryPage<T> q) {
		Direction direction = getDefaultASC();
		String orderBy = getDefaultOrder();
		if (StringUtils.isNotBlank(q.getOrderBy())) {
			orderBy = q.getOrderBy();
		}
		if (StringUtils.equalsIgnoreCase(q.getOrderAsc(), "ASC")) {
			direction = Direction.ASC;
		}
		return Sort.by(direction, orderBy);
	}

	protected Pageable setPageable(BaseQueryPage<T> q) {
		return PageRequest.of(q.getPageNo()-1, q.getPageSize(), this.setSort(q));
	}

	protected Example<M> setExample(BaseQueryPage<T> q)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		M m = beanTransUtil.getM(getClass());
		BeanUtils.copyProperties(m, q);
		// 创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching(); // 构建对象
		// 创建实例
		return Example.of(m, matcher);
	}

	protected ArrayList<T> getPage(BaseQueryPage<T> q, MongoRepository<M, String> repository) {
		ArrayList<T> list = new ArrayList<T>();
		try {
			Pageable pageable = this.setPageable(q);
			Example<M> example = this.setExample(q);
			List<M> content = repository.findAll(example, pageable).getContent();
			beanTransUtil.listTrans(list, content, getClass());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	protected ArrayList<T> getList(BaseQueryPage<T> q, MongoRepository<M, String> repository) {
		ArrayList<T> list = new ArrayList<T>();
		try {
			Example<M> example = this.setExample(q);
			List<M> result = repository.findAll(example);
			beanTransUtil.listTrans(list, result, getClass());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return list;
	}

	protected long getCount(BaseQueryPage<T> q, MongoRepository<M, String> repository) {
		try {
			Example<M> example = this.setExample(q);
			return repository.count(example);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	protected int save(T t, MongoRepository<M, String> repository) {
		try {
			M m = beanTransUtil.getM(getClass());
			BeanUtils.copyProperties(m, t);
			repository.save(m);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
}
