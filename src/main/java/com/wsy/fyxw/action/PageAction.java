package com.wsy.fyxw.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wsy.fyxw.domain.User;

/**
 * 仅页面跳转
 * 
 * @author Sally
 *
 */
@Controller
public class PageAction extends BaseAction {

	@GetMapping("/login")
	public String loginPage(String username, Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
			return "redirect:/index";
		}
		model.addAttribute("username", username);
		return "login/login";
	}

	@GetMapping("/regist")
	public String registPage(Model model) {
		return "regist/regist";
	}

	@GetMapping(value = { "/", "/index" })
	public String index(User user, Model model) {
		return "index";
	}

	@GetMapping("/user/nicknameSetting")
	public String nicknamePage(User user, Model model, String operate) {
		if (StringUtils.isBlank(operate)) {
			if (StringUtils.isNotBlank(user.getNickname())) {
				operate = "change";
			} else {
				operate = "set";
			}
		}
		model.addAttribute("operate", operate);
		return "user/nicknameSetting";
	}
	
	@GetMapping("/user/passwordSetting")
	public String passwordPage() {
		return "user/passwordSetting";
	}
	
	@GetMapping("/system/cacheManagement")
	public String cacheMaPage() {
		return "system/cacheManagement";
	}
}
