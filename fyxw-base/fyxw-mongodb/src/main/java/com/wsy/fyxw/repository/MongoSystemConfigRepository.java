package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.domain.mongo.MongoSystemConfig;

public interface MongoSystemConfigRepository extends MongoRepository<MongoSystemConfig, String> {
	
	public SystemConfig findByAttrKey(String attrKey);
}
