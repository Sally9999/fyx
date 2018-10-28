package com.wsy.fyxw.domain;

public class LogInfo extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1023889915344811643L;
	private String account;
	private String type;
	private String result;
	private String message;

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

	@Override
	public String toString() {
		return "LogInfo [account=" + account + ", type=" + type + ", result=" + result + ", message=" + message + "]";
	}

}
