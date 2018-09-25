package com.wsy.fyxw.action;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.dto.RoleTreeDto;
import com.wsy.fyxw.enums.EnumUserStatus;
import com.wsy.fyxw.query.UserQuery;
import com.wsy.fyxw.service.RoleService;
import com.wsy.fyxw.service.UserService;

/**
 * 用户管理
 * 
 * @author Sally
 *
 */
@Controller
public class UserMaAction extends BaseAction {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@GetMapping("/userMa/userManagement")
	public String userListPage() {
		return "userMa/userManagement";
	}

	@GetMapping("/userMa/userDetail")
	public String userDetailPage(String account, Model model) {
		User user = userService.getUserByAccount(account);
		model.addAttribute("user", user);
		return "userMa/userDetail";
	}

	@PostMapping("/userMa/roleList")
	@ResponseBody
	public ArrayList<RoleTreeDto> getRoleList(String account, Model model) {
		// 根据account查询所有启用角色及是否具有权限
		return roleService.getRoleListWithAccount(account);
	}

	@PostMapping("/userMa/getPage")
	@ResponseBody
	public JSONObject getUserListByPage(UserQuery query, Model model) {
		JSONObject result = new JSONObject();
		query = userService.getUserPage(query);
		model.addAttribute("query", query);
		result.put("total", query.getTotalRecord());
		result.put("rows", query.getResultItems());
		return result;
	}

	@PostMapping("/userMa/rolesSave")
	@ResponseBody
	public ResultInfo saveRoles(User user, @RequestParam("roleCodes") String roleCodes,
			@RequestParam("accountStr") String accountStr) {
		ArrayList<String> roleCodeList = new ArrayList<String>();
		String[] strs = roleCodes.split(",");
		for (String code : strs) {
			if (StringUtils.isNotBlank(code)) {
				roleCodeList.add(code);
			}
		}
		return userService.saveRoles(roleCodeList, accountStr, user.getAccount());
	}

	@PostMapping("/userMa/disabledUser")
	@ResponseBody
	public ResultInfo disabledUser(User user, String accountStr) {
		User change = new User();
		change.setAccount(accountStr);
		change.setOperator(user.getAccount());
		change.setStatus(EnumUserStatus.LOCKED.getCode());
		return userService.changeStatus(change);
	}

	@PostMapping("/userMa/enabledUser")
	@ResponseBody
	public ResultInfo enabledRole(User user, String accountStr) {
		User change = new User();
		change.setAccount(accountStr);
		change.setOperator(user.getAccount());
		change.setStatus(EnumUserStatus.NORMAL.getCode());
		return userService.changeStatus(change);
	}
}
