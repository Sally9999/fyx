package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.RelationUserRole;

public class RelationUserRoleQuery extends BaseQueryPage<RelationUserRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 612098563167444155L;
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
