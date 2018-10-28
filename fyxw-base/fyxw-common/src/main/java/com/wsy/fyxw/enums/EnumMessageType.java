package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 消息类型
 * 
 * @author Sally
 *
 */
public enum EnumMessageType {
	REGIST_SUCCESS("registSuccess", "注册成功");

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

	private EnumMessageType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getTypeByCode(String code) {
		for (EnumMessageType type : EnumMessageType.values()) {
			if (StringUtils.equals(code, type.getCode())) {
				return type.getValue();
			}
		}
		return null;
	}
}
