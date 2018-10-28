package com.wsy.fyxw.mq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.LogInfo;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.service.LogService;

@Component
public class LogReceiver {

	@Autowired
	private LogService logService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@RabbitListener(queues = "logQueue")
	public void writeLog(LogInfo log) {
		logger.info("logQueue receive:{}", log.toString());
		logService.writeLog(log);
	}

	@RabbitListener(queues = "fanout.registLog")
	public void registLog(String account) {
		logger.info("fanout.registLog receive:{}", account);
		LogInfo log = new LogInfo();
		log.setAccount(account);
		log.setType(EnumLogType.REGIST.getCode());
		log.setResult(EnumCommonResult.SUCCESS.getCode());
		log.setMessage(account + "注册成功");
		log.setOperator(account);
		logService.writeLog(log);
	}
}
