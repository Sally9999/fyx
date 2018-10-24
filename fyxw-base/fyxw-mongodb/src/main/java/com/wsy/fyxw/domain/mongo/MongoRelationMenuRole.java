package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.RelationMenuRole;

@Document(collection="relation_menu_role")
public class MongoRelationMenuRole extends RelationMenuRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7868796579038547603L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
