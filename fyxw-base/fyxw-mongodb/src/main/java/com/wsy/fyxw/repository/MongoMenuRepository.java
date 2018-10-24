package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.domain.mongo.MongoMenu;

public interface MongoMenuRepository extends MongoRepository<MongoMenu, String>{

	public Menu findByMenuCode(String menuCode);
	
	public void deleteByMenuCode(String menuCode);
}
