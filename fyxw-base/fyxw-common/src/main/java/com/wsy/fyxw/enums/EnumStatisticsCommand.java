package com.wsy.fyxw.enums;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据统计命令
 * 
 * @author Sally
 *
 */
public enum EnumStatisticsCommand {

	USER_COUNT("userCount", "用户总量"), 
	USER_REGIST_TENDENCY("userRegistTendency", "用户注册走势");

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

	private EnumStatisticsCommand(String code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getCommandByCode(String code) {
		for (EnumStatisticsCommand command : EnumStatisticsCommand.values()) {
			if (StringUtils.equals(code, command.getCode())) {
				return command.getValue();
			}
		}
		return null;
	}

	public static ArrayList<String> getCommandList(String code) {
		ArrayList<String> list = new ArrayList<String>();
		if (null != getCommandByCode(code)) {
			list.add(code);
		} else if (StringUtils.equals(code, EnumStatisticsCommandType.ALL.getCode())) {
			for (EnumStatisticsCommand command : EnumStatisticsCommand.values()) {
				list.add(command.getCode());
			}
		}
		return list;
	}
}
