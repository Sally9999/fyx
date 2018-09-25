package com.wsy.fyxw.enums;

/**
 * yes or no
 * @author Sally
 *
 */
public enum EnumYesOrNo {

	YES("Y","是"),
	NO("N","否");
	
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

	private EnumYesOrNo(String code, String value) {
		this.code = code;
		this.value = value;
	}
}
