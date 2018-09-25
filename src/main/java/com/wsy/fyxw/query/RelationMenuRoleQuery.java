package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.RelationMenuRole;

public class RelationMenuRoleQuery extends BaseQueryPage<RelationMenuRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3183234907871432149L;
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
