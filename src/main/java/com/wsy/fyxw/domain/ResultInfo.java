package com.wsy.fyxw.domain;

import java.io.Serializable;

public class ResultInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1008931193308194697L;
	private String code;
	private String value;

	public ResultInfo() {
		
	}
	
	public ResultInfo(String code, String value) {
		super();
		this.code = code;
		this.value = value;
	}

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
}
