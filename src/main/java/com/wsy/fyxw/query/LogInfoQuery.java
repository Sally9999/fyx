package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.LogInfo;

public class LogInfoQuery  extends BaseQueryPage<LogInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7166173068219795093L;
	private Long id;
	private String account;
	private String type;
	private String result;
	private String message;
	private String operator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
