package com.wsy.fyxw.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.wsy.fyxw.domain.MessageInfo;

@Document(collection = "message_info")
public class MongoMessageInfo extends MessageInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6477653677582268625L;
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
