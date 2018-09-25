package com.wsy.fyxw.query;

import java.io.Serializable;

public class RelationUserRoleQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 612098563167444155L;
	private Long id;
	private String account;
	private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
