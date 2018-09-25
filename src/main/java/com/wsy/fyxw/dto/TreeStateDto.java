package com.wsy.fyxw.dto;

public class TreeStateDto {

    /**
     * 是否选中一个节点，用复选框样式图标表示。Default: false
     */
    private boolean checked;

    /**
     * 是否禁用节点(不可选择、可扩展或可检查)。Default: false
     */
    private boolean disabled;

    /**
     * 是否扩展一个节点，即打开。优先于全局选项级别。Default: false
     */
    private boolean expanded;

    /**
     * 是否选择一个节点。Default: false
     */
    private boolean selected;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
