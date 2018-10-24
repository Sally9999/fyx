package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.RelationUserRole;

@Document(collection="relation_user_role")
public class MongoRelationUserRole extends RelationUserRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7052819399744215796L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
