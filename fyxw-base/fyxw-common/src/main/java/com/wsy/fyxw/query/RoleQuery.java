package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.Role;

public class RoleQuery extends BaseQueryPage<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6892535820458957764L;
	private String roleCode;
	private String roleName;
	private String status;
	private String type;
	private String account;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
