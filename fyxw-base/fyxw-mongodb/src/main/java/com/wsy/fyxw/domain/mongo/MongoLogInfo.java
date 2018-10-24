package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.LogInfo;

@Document(collection="log_info")
public class MongoLogInfo extends LogInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309733838945777870L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
