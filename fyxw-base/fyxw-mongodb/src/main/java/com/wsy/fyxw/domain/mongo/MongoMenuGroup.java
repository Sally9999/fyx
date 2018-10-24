package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.MenuGroup;

@Document(collection="menu_group")
public class MongoMenuGroup extends MenuGroup {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1177949253068032724L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
