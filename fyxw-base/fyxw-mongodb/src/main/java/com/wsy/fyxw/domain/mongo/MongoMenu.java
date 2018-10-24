package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.Menu;

@Document(collection="menu_info")
public class MongoMenu extends Menu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9173770296420453105L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
