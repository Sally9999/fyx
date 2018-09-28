package com.wsy.fyxw.action.client;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumUserResult;
import com.wsy.fyxw.service.UserService;

/**
 * 用户相关设置
 * 
 * @author Sally
 *
 */
@RestController
public class UserAction extends BaseAction {

	@Autowired
	private UserService userService;

	@PostMapping("/user/setNickName")
	public ResultInfo setNickName(User user, @RequestParam("nickName") String nickName, HttpServletRequest request) {
		user.setNickname(nickName);
		if (userService.updateUser(user) > 0) {
			// 更新session
			sessionUtil.updateSession(user, request);
			// 记日志
			logUtil.writeLogCommon(user.getAccount(), EnumLogType.NICKNAME_SET.getCode(), user.getAccount(), EnumCommonResult.SUCCESS);
			return new ResultInfo(EnumUserResult.SUCCESS.getCode(), EnumUserResult.SUCCESS.getValue());
		}
		return new ResultInfo(EnumUserResult.FAILED.getCode(), EnumUserResult.FAILED.getValue());
	}

	@PostMapping("user/changePwd")
	public ResultInfo changePassword(User user, @RequestParam("newPwd") String newPwd,
			@RequestParam("orgPwd") String orgPwd,HttpServletRequest request) {
		if (StringUtils.equals(user.getPwd(), orgPwd)) {
			ResultInfo result = userService.changePwd(user, newPwd, true);
			if(StringUtils.equals(result.getCode(), EnumCommonResult.SUCCESS.getCode())) {
				// 更新session
				user.setPwd(newPwd);
				sessionUtil.updateSession(user, request);
			}
			return result;
		} else {
			return new ResultInfo(EnumUserResult.ORG_PWD_ERROR.getCode(), EnumUserResult.ORG_PWD_ERROR.getValue());
		}
	}
}
