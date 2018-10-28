package com.wsy.fyxw.mq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.MessageInfo;
import com.wsy.fyxw.enums.EnumDefaultRoleType;
import com.wsy.fyxw.enums.EnumMessageStatus;
import com.wsy.fyxw.enums.EnumMessageType;
import com.wsy.fyxw.service.MessageService;
import com.wsy.fyxw.service.SystemConfigService;

@Component
public class MessageReceiver {

	@Autowired
	private MessageService messageService;

	@Autowired
	private SystemConfigService systemConfigService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String MESSAGE_TEMPLATE = "_mt";

	@RabbitListener(queues = "fanout.registMessage")
	public void registMessage(String account) {
		logger.info("fanout.registMessage receive:{}", account);
		MessageInfo message = new MessageInfo();
		message.setReceiver(account);
		message.setSender(EnumDefaultRoleType.SYSTEM.getCode());
		message.setType(EnumMessageType.REGIST_SUCCESS.getCode());
		String template = this.getTemplate(EnumMessageType.REGIST_SUCCESS.getCode() + MESSAGE_TEMPLATE);
		message.setMessage(template.replaceAll("#account#", account));
		message.setStatus(EnumMessageStatus.UNREAD.getCode());
		messageService.sendMessage(message);
	}

	private String getTemplate(String key) {
		return systemConfigService.getSystemConfigByKey(key);
	}
}
