package com.wsy.fyxw.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 日志类型
 * @author Sally
 *
 */
public enum EnumLogType {
	// 普通用户日志
	LOGIN("login", "登录日志"),
	USER_INFO_CHANGED("userInfoChanged","会员信息修改"),
	NICKNAME_SET("nicknameSet","昵称设置"),
	MOBILE_SET("mobileSet","手机设置"),
	EMAIL_SET("emailSet","邮箱设置"),
	PASSWORD_CHANGED("passwordChanged","密码修改"),
	
	// 管理员日志
	MENU_GROUP_ADD("menuGroupAdd","菜单组创建"),
	MENU_GROUP_UPDATE("menuGroupUpdate","菜单组更新"),
	MENU_GROUP_DELETE("menuGroupDelete","菜单组删除"),
	MENU_ADD("menuAdd","菜单创建"),
	MENU_UPDATE("menuUpdate","菜单更新"),
	MENU_DELETE("menuDelete","菜单删除"),
	ROLE_ADD("roleAdd","角色创建"),
	ROLE_UPDATE("roleUpdate","角色更新"),
	ROLE_DELETE("roleUpdate","角色删除"),
	ROLE_STATUS_CHANGE("roleStatusChange","角色状态变更"),
	ROLE_AUTHORITY_CHANGE("roleAuthorityChange","角色权限变更"),
	USER_STATUS_CHANGE("userStatusChange","用户状态变更"),
	USER_ROLE_CHANGE("userRoleChange","用户角色变更"),
	SYSTEM_CONFIG_CHANGE("systemConfigChange","系统配置更新"),
	SYSTEM_CONFIG_STATUS_CHANGE("systemConfigStatusChange","系统配置状态更新");

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private EnumLogType(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getTypeByCode(String code) {
		for (EnumLogType type : EnumLogType.values()) {
			if (StringUtils.equals(code, type.getCode())) {
				return type.getValue();
			}
		}
		return null;
	}
}
