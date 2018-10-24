package com.wsy.fyxw.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1409612761165769055L;
	private String id;
	private Date gmtCreate;
	private Date gmtModify;
	private String operator;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
