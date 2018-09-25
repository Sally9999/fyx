package com.wsy.fyxw.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.service.UserService;

/**
 * 注册
 * 
 * @author Sally
 *
 */
@RestController
public class RegisterAction extends BaseAction {

	@Autowired
	private UserService userService;

	@PostMapping("/createAccount")
	public ResultInfo createAccount(Model model, User user) {
		return userService.createUser(user);
	}
}
