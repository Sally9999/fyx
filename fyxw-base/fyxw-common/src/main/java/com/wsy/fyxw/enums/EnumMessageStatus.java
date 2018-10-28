package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 消息状态
 * 
 * @author Sally
 *
 */
public enum EnumMessageStatus {

	READ("read", "已读"), 
	UNREAD("unread", "未读"), 
	DRAFT("draft", "草稿");

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private EnumMessageStatus(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getStatusByCode(String code) {
		for (EnumMessageStatus status : EnumMessageStatus.values()) {
			if (StringUtils.equals(code, status.getCode())) {
				return status.getValue();
			}
		}
		return null;
	}
}
