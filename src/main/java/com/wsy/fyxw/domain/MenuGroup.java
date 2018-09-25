package com.wsy.fyxw.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MenuGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7441793087171804037L;
	private Long id;
	private String groupCode;
	private String groupName;
	private String icon;
	private String status;
	private Integer sort;
	private Date gmtCreate;
	private Date gmtModify;
	private ArrayList<Menu> menuList;
	// 用于日志记录
	private String operator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
