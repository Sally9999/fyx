package com.wsy.fyxw.util;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.User;

@Component("sessionUtil")
public class SessionUtil {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void updateSession(User user, HttpServletRequest request) {
		try {
			User currUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			BeanUtils.copyProperties(currUser, user);
		} catch (IllegalAccessException e) {
			logger.error("update session failed:{}", e.getMessage());
		} catch (InvocationTargetException e) {
			logger.error("update session failed:{}", e.getMessage());
		}
	}
}
