package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用状态
 * @author Sally
 *
 */
public enum EnumCommonStatus {

	NORMAL("N","正常"),
	DISABLED("D","禁用");
	
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

	private EnumCommonStatus(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static String getStatusByCode(String code) {
		for(EnumCommonStatus status: EnumCommonStatus.values()) {
			if(StringUtils.equals(code, status.getCode())) {
				return status.getValue();
			}
		}
		return null;
	}
}
