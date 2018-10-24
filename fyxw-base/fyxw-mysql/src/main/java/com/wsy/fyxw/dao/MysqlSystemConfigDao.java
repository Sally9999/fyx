package com.wsy.fyxw.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.query.SystemConfigQuery;
import com.wsy.fyxw.repository.MysqlSystemConfigRepository;

@Component("mysqlSystemConfigDao")
public class MysqlSystemConfigDao implements SystemConfigDao {
	@Autowired
	private MysqlSystemConfigRepository repository;

	@Override
	public ArrayList<SystemConfig> getPage(SystemConfigQuery q) {
		return repository.getPage(q);
	}

	@Override
	public ArrayList<SystemConfig> getList(SystemConfigQuery q) {
		return repository.getList(q);
	}

	@Override
	public long getCount(SystemConfigQuery q) {
		return repository.getCount(q);
	}

	@Override
	public SystemConfig getByCode(String code) {
		return repository.getByCode(code);
	}

	@Override
	public int update(SystemConfig t) {
		return repository.update(t);
	}

	@Override
	public int add(SystemConfig t) {
		return repository.add(t);
	}

	@Override
	public int deleteByCode(String code) {
		return repository.deleteByCode(code);
	}

	@Override
	public int changeStatusByCode(SystemConfig t) {
		return repository.changeStatusByCode(t);
	}

	@Override
	public String getSystemConfigByKey(String key) {
		return repository.getSystemConfigByKey(key);
	}
}
