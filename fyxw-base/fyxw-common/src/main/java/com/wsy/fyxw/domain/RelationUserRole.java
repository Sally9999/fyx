package com.wsy.fyxw.domain;

public class RelationUserRole extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3496868087092806123L;
	private String account;
	private String roleCode;

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
