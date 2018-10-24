package com.wsy.fyxw.domain;

public class SystemConfig extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 882971997444617959L;
	private String attrKey;
	private String attrValue;
	private String status;
	private String required;
	private String memo;

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
