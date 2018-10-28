package com.wsy.fyxw.action.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.service.MessageService;

/**
 * 首页
 * 
 * @author Sally
 *
 */
@RestController
public class IndexAction extends BaseAction {

	@Autowired
	private MessageService messageService;

	@PostMapping("/getUnreadMsg")
	public Long getUnreadMsg(User user, Model model) {
		return messageService.countUnread(user.getAccount());
	}

}
