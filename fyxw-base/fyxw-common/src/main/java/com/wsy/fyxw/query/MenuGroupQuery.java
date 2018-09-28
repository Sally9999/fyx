package com.wsy.fyxw.query;

import com.wsy.fyxw.domain.MenuGroup;

public class MenuGroupQuery extends BaseQueryPage<MenuGroup> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 687563729099330810L;
	private Long id;
	private String groupCode;
	private String groupName;
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
