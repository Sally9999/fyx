package com.wsy.fyxw.domain;

import java.io.Serializable;

public class RelationUserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3496868087092806123L;
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
