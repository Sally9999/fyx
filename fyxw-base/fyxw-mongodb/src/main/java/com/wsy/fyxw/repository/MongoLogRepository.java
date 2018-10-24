package com.wsy.fyxw.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wsy.fyxw.domain.mongo.MongoLogInfo;

public interface MongoLogRepository extends MongoRepository<MongoLogInfo, String> {

}
