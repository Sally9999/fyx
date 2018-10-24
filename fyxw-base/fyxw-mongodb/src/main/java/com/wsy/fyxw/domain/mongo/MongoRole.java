package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.Role;

@Document(collection="role_info")
public class MongoRole extends Role {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6414970956884046461L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
