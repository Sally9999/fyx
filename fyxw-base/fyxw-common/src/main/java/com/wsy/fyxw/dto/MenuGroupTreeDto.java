package com.wsy.fyxw.dto;

import java.util.List;

import com.wsy.fyxw.domain.MenuGroup;

/**
 * 树形菜单DTO-菜单组
 */
public class MenuGroupTreeDto{

    private String id;

    private String code;

    private String text;

    private List<MenuTreeDto> nodes;

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

    public List<MenuTreeDto> getNodes() {
        return nodes;
    }

    public void setNodes(List<MenuTreeDto> nodes) {
        this.nodes = nodes;
    }

	public MenuGroupTreeDto(MenuGroup group) {
		this.id = group.getId();
		this.code = group.getGroupCode();
		this.text = group.getGroupName();
	}
    
}
