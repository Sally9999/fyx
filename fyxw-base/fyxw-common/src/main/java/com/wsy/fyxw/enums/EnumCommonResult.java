package com.wsy.fyxw.enums;

/**
 * 通用操作结果
 * @author Sally
 *
 */
public enum EnumCommonResult {

	SUCCESS("success","操作成功"),
	FAILED("failed","操作失败"),
	CODE_EXIST("codeExist","编码重复"),
	CODE_NULL("codeNull","编码为空"),
	ID_NULL("idNull","id为空"),
	NOT_FOUND("notFound","查找对象不存在"),
	CANT_DELETE_SYSTEM_CONF("cantDeleteSystemConf","不能删除系统默认设置"),
	CANT_OPERATE_SYSTEM_CONF("cantOperateSystemConf","不能修改系统默认设置"),
	ROLE_CODE_NULL("roleCodeNull","角色编码为空"),
	STATUS_NULL("statusNull","状态为空"),
	SYSTEM_ERROR("systemError","系统错误");
	
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

	private EnumCommonResult(String code, String value) {
		this.code = code;
		this.value = value;
	}
}
