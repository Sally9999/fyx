package com.wsy.fyxw.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.enums.EnumYesOrNo;

/**
 * 树形菜单DTO-菜单
 */
public class MenuTreeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7112335371397133793L;

	private String id;

	private String code;

	private String text;

	private TreeStateDto state;

	public TreeStateDto getState() {
		return state;
	}

	public void setState(TreeStateDto state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public MenuTreeDto(Menu menu) {
		this.id = menu.getId();
		this.code = menu.getMenuCode();
		this.text = menu.getMenuName();
		if(StringUtils.equalsIgnoreCase(menu.getHasAuthority(), EnumYesOrNo.YES.getCode())) {
			this.state = new TreeStateDto();
            this.state.setChecked(true);
		}
	}
}
