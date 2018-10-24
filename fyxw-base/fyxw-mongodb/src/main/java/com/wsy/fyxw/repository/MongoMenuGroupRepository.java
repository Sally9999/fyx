package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.mongo.MongoMenuGroup;

public interface MongoMenuGroupRepository extends MongoRepository<MongoMenuGroup, String> {

	public MenuGroup findByGroupCode(String groupCode);
	
	public void deleteByGroupCode(String groupCode);
}
