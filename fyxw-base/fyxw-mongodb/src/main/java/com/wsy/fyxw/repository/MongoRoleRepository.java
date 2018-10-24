package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.domain.mongo.MongoRole;

public interface MongoRoleRepository extends MongoRepository<MongoRole, String> {

	public Role findByRoleCode(String roleCode);

	public int deleteByRoleCode(String code);
}
