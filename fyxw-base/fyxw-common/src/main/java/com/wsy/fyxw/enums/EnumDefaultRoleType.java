package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 默认角色类型
 * 
 * @author Sally
 *
 */
public enum EnumDefaultRoleType {

	ADMIN("admin", "管理员"), 
	COMMON("common", "普通用户"),
	SYSTEM("system","系统");

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

	private EnumDefaultRoleType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getTypeByCode(String code) {
		for (EnumDefaultRoleType type : EnumDefaultRoleType.values()) {
			if (StringUtils.equals(code, type.getCode())) {
				return type.getValue();
			}
		}
		return null;
	}
}
