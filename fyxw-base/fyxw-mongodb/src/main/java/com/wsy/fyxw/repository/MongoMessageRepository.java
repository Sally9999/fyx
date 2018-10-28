package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.mongo.MongoMessageInfo;

public interface MongoMessageRepository extends MongoRepository<MongoMessageInfo, String> {

}
