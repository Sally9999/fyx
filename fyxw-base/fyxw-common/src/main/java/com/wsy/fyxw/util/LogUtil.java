package com.wsy.fyxw.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumUserResult;
import com.wsy.fyxw.query.LogInfoQuery;
import com.wsy.fyxw.service.LogService;

@Component("logUtil")
public class LogUtil {

	@Autowired
	private LogService logService;

	/**
	 * 写入登录日志
	 * 
	 * @param user
	 * @param result
	 */
	public void writeLoginLog(User user, EnumUserResult result) {
		LogInfo log = new LogInfo();
		log.setAccount(user.getAccount());
		log.setType(EnumLogType.LOGIN.getCode());
		log.setResult(result.getCode());
		log.setMessage(result.getValue());
		logService.writeLog(log);
	}

	/**
	 * 查日志条数
	 * 
	 * @param account
	 * @param type
	 * @param result
	 * @return
	 */
	public Long getLogCount(String account, String type, String result) {
		LogInfoQuery query = new LogInfoQuery();
		query.setAccount(account);
		query.setType(type);
		query.setResult(result);
		return logService.getLogCount(query);
	}

	/**
	 * 通用日志记录
	 * 
	 * @param account
	 * @param logType
	 * @param operator
	 * @param result
	 */
	public void writeLogCommon(String account, String logType, String operator, EnumCommonResult result) {
		LogInfo log = new LogInfo();
		log.setAccount(account);
		log.setType(logType);
		log.setResult(result.getCode());
		log.setMessage(result.getValue());
		log.setOperator(operator);
		logService.writeLog(log);
	}
	
	/**
	 * 通用成功日志
	 * @param account
	 * @param logType
	 * @param operator
	 * @param message
	 */
	public void writeSuccessLogCommon(String account, String logType, String operator, String message) {
		LogInfo log = new LogInfo();
		log.setAccount(account);
		log.setType(logType);
		log.setResult(EnumCommonResult.SUCCESS.getCode());
		log.setMessage(message);
		log.setOperator(operator);
		logService.writeLog(log);
	}
}
