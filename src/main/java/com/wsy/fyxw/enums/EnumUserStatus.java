package com.wsy.fyxw.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户状态
 * 
 * @author Sally
 *
 */
public enum EnumUserStatus {

	NORMAL("N", "正常"), LOCKED("L", "锁定"), CANCEL("C", "注销");

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

	private EnumUserStatus(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getStatusByCode(String code) {
		for (EnumUserStatus status : EnumUserStatus.values()) {
			if (StringUtils.equals(code, status.getCode())) {
				return status.getValue();
			}
		}
		return null;
	}

	public static Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		for (EnumUserStatus status : EnumUserStatus.values()) {
			map.put(status.getCode(), status.getValue());
		}
		return map;
	}
}
