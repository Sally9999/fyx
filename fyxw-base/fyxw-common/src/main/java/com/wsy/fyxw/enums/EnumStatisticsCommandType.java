package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据统计命令类型
 * 
 * @author Sally
 *
 */
public enum EnumStatisticsCommandType {

	ALL("all", "所有"), 
	USER("user", "用户相关");

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

	private EnumStatisticsCommandType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getTypeByCode(String code) {
		for (EnumStatisticsCommandType type : EnumStatisticsCommandType.values()) {
			if (StringUtils.equals(code, type.getCode())) {
				return type.getValue();
			}
		}
		return null;
	}
}
