package com.wsy.fyxw.domain;

import java.io.Serializable;

public class RelationMenuRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5069332687891267383L;
	private Long id;
	private String menuCode;
	private String roleCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
