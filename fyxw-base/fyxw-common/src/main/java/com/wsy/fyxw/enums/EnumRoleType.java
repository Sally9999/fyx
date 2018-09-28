package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 角色类型
 * 
 * @author Sally
 *
 */
public enum EnumRoleType {

	DEFAULT("default", "系统默认"), 
	USER_DEFINED("userDefined", "用户自定义");

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

	private EnumRoleType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getTypeByCode(String code) {
		for (EnumRoleType type : EnumRoleType.values()) {
			if (StringUtils.equals(code, type.getCode())) {
				return type.getValue();
			}
		}
		return null;
	}
}
