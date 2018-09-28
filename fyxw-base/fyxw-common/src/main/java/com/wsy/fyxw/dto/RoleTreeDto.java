package com.wsy.fyxw.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.enums.EnumYesOrNo;

/**
 * 树形菜单DTO-角色
 */
public class RoleTreeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1594110698899692110L;

	private Long id;

	private String code;

	private String text;

	private TreeStateDto state;

	public TreeStateDto getState() {
		return state;
	}

	public void setState(TreeStateDto state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public RoleTreeDto(Role role) {
		this.id = role.getId();
		this.code = role.getRoleCode();
		this.text = role.getRoleName();
		if (StringUtils.equalsIgnoreCase(role.getHasAuthority(), EnumYesOrNo.YES.getCode())) {
			this.state = new TreeStateDto();
			this.state.setChecked(true);
		}
	}
}
