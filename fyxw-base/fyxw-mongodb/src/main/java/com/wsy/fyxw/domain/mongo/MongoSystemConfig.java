package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.SystemConfig;

@Document(collection="system_config")
public class MongoSystemConfig extends SystemConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4000249499613151890L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
