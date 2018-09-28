package com.wsy.fyxw.domain;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7678341810055650943L;
	private Long id;
	private String roleCode;
	private String roleName;
	private String status;
	private String type;
	private Date gmtCreate;
	private Date gmtModify;
	// 用于日志记录
	private String operator;
	private String hasAuthority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getHasAuthority() {
		return hasAuthority;
	}

	public void setHasAuthority(String hasAuthority) {
		this.hasAuthority = hasAuthority;
	}
}
