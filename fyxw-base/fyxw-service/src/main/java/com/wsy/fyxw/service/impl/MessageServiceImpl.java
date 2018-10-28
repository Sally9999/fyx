package com.wsy.fyxw.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wsy.fyxw.dao.MessageDao;
import com.wsy.fyxw.dao.factory.DaoFactory;
import com.wsy.fyxw.domain.MessageInfo;
import com.wsy.fyxw.enums.EnumMessageStatus;
import com.wsy.fyxw.query.MessageQuery;
import com.wsy.fyxw.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	public MessageServiceImpl(DaoFactory daoFactory, @Value("${datasource.type}") String DATA_SOURCE_TYPE) {
		super();
		this.messageDao = (MessageDao) daoFactory.getDao(DATA_SOURCE_TYPE + "MessageDao");
	}

	@Override
	public int sendMessage(MessageInfo message) {
		return messageDao.add(message);
	}

	@Override
	public Long countUnread(String receiver) {
		MessageQuery query = new MessageQuery();
		query.setReceiver(receiver);
		query.setStatus(EnumMessageStatus.UNREAD.getCode());
		return messageDao.getCount(query);
	}

	@Override
	public MessageQuery getMessagePage(MessageQuery query) {
		long count = messageDao.getCount(query);
		ArrayList<MessageInfo> list = messageDao.getPage(query);
		query.setTotalRecord(count);
		query.setResultItems(list);
		return query;
	}

}
