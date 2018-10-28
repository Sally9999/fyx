package com.wsy.fyxw.mq.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQSenderConf {

	@Bean(name="logQueue")
	public Queue logQueue() {
		return new Queue("logQueue");
	}
	
	@Bean(name="registLog")
	public Queue registLogQueue() {
		return new Queue("fanout.registLog");
	}
	
	@Bean(name="registMessage")
	public Queue registMessageQueue() {
		return new Queue("fanout.registMessage");
	}
	
	@Bean
	public FanoutExchange registExchange() {
		return new FanoutExchange("registExchange");
	}
	
	@Bean
	public Binding bingRegistLog(@Qualifier("registLog") Queue registLogQueue, FanoutExchange registExchange ) {
		return BindingBuilder.bind(registLogQueue).to(registExchange);
	}
	@Bean
	public Binding bingRegistMessage(@Qualifier("registMessage") Queue registMessageQueue, FanoutExchange registExchange ) {
		return BindingBuilder.bind(registMessageQueue).to(registExchange);
	}
}
