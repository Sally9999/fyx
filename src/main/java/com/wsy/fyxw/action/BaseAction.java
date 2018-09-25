package com.wsy.fyxw.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wsy.fyxw.util.LogUtil;
import com.wsy.fyxw.util.SessionUtil;

public class BaseAction {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected LogUtil logUtil;
	
	@Autowired
	protected SessionUtil sessionUtil;
}
