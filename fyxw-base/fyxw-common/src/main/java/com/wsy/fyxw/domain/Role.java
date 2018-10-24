package com.wsy.fyxw.domain;

public class Role extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7678341810055650943L;
	private String roleCode;
	private String roleName;
	private String status;
	private String type;
	private String hasAuthority;

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

	public String getHasAuthority() {
		return hasAuthority;
	}

	public void setHasAuthority(String hasAuthority) {
		this.hasAuthority = hasAuthority;
	}
}
