package com.wsy.fyxw.query;

import java.util.ArrayList;

import com.wsy.fyxw.domain.Menu;

public class MenuQuery extends BaseQueryPage<Menu> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8311951415932575470L;
	private Long id;
	private String menuGroup;
	private String menuCode;
	private String menuName;
	private String status;
	private String roleCode;
	private ArrayList<String> roleCodeList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public ArrayList<String> getRoleCodeList() {
		return roleCodeList;
	}

	public void setRoleCodeList(ArrayList<String> roleCodeList) {
		this.roleCodeList = roleCodeList;
	}
}
