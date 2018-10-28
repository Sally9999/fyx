package com.wsy.fyxw.service;

import com.wsy.fyxw.domain.MessageInfo;
import com.wsy.fyxw.query.MessageQuery;

public interface MessageService {

	/**
	 * 消息发送
	 * 
	 * @param message
	 * @return
	 */
	public int sendMessage(MessageInfo message);

	/**
	 * 未读消息数量
	 * 
	 * @param receiver
	 * @return
	 */
	public Long countUnread(String receiver);
	
	/**
	 * 分页查消息列表
	 * 
	 * @param query
	 * @return
	 */
	public MessageQuery getMessagePage(MessageQuery query);

}
