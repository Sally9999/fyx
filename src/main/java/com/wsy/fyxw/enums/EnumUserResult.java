package com.wsy.fyxw.enums;

/**
 * 与用户相关的操作结果
 * @author Sally
 *
 */
public enum EnumUserResult {

	SUCCESS("success","成功了诶"),
	FAILED("failed","好像哪里不对"),
	USER_NOT_FIND("userNotFind","查无此人啊"),
	PWD_NOT_MATCH("pwdNotMatch","哦哦，用户名错了呢还是密码错了呢"),
	ACCOUNT_NULL("accountNull","用户名都没有怎么玩"),
	PWD_NULL("pwdNull","给个密码先"),
	STATUS_ERROR("statusError","这个账号有点问题哦"),
	USER_EXIST("user_exist","已有人抢占了先机，换个名字试试吧"),
	ORG_PWD_ERROR("orgPwdError","原始密码不对哦"),
	NEW_PWD_EQ_ORG("newPwdEqOrg","新密码跟原始密码都是一样的，有啥好变更的"),
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

	private EnumUserResult(String code, String value) {
		this.code = code;
		this.value = value;
	}
}
