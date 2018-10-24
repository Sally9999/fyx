package com.wsy.fyxw.domain;

public class Menu extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7550459841978870434L;
	private String menuGroup;
	private String menuCode;
	private String menuName;
	private String icon;
	private String url;
	private String status;
	private Integer sort;
	private String hasAuthority;
	private String roleCode;

	public String getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(String menuGroup) {
		this.menuGroup = menuGroup;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getHasAuthority() {
		return hasAuthority;
	}

	public void setHasAuthority(String hasAuthority) {
		this.hasAuthority = hasAuthority;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

}
