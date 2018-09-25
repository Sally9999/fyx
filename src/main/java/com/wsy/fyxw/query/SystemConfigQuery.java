package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.SystemConfig;

public class SystemConfigQuery extends BaseQueryPage<SystemConfig> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2764754741305803890L;
	private Long id;
	private String attrKey;
	private String attrValue;
	private String status;
	private String required;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttrKey() {
		return attrKey;
	}

	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

}
