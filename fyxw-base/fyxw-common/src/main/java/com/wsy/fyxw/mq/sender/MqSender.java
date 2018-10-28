package com.wsy.fyxw.mq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.LogInfo;

@Component
public class MqSender {

	@Autowired
	private AmqpTemplate template;

	public void sendLog(LogInfo log) {
		template.convertAndSend("logQueue", log);
	}

	public void sendRegist(String account) {
		template.convertAndSend("registExchange", "", account);
	}
}
