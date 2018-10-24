package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.mongo.MongoUser;

public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

}
