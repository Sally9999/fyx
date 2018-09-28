package com.wsy.fyxw.action.admin;

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
import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.Role;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.dto.MenuGroupTreeDto;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.query.RoleQuery;
import com.wsy.fyxw.service.MenuService;
import com.wsy.fyxw.service.RoleService;

@Controller
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@GetMapping("/role/roleManagement")
	public String roleListPage() {
		return "role/roleManagement";
	}

	@GetMapping("/role/roleCreation")
	public String roleAddPage() {
		return "role/roleAdd";
	}

	@GetMapping("/role/roleSetting")
	public String roleDetailPage(String roleCode, Model model) {
		Role role = roleService.getRoleByCode(roleCode);
		model.addAttribute("role", role);
		return "role/roleDetail";
	}

	@GetMapping("/role/authoritySetting")
	public String authoritySettingPage(String roleCode, Model model) {
		// 根据roleCode查询所有启用菜单及是否具有权限
		Role role = roleService.getRoleByCode(roleCode);
		ArrayList<MenuGroupTreeDto> groupTree = menuService.getMenuGroupWithRole(roleCode);
		model.addAttribute("role", role);
		model.addAttribute("groupTree", groupTree);
		return "role/authority";
	}

	@PostMapping("/role/getPage")
	@ResponseBody
	public JSONObject getRoleListByPage(RoleQuery query, Model model) {
		JSONObject result = new JSONObject();
		query = roleService.getRolePage(query);
		model.addAttribute("query", query);
		result.put("total", query.getTotalRecord());
		result.put("rows", query.getResultItems());
		return result;
	}

	@PostMapping("/role/submit")
	@ResponseBody
	public ResultInfo saveRole(User user, Role role) {
		role.setOperator(user.getAccount());
		return roleService.saveRole(role);
	}

	@PostMapping("/role/authoritySave")
	@ResponseBody
	public ResultInfo saveAuthority(User user, @RequestParam("menuCodes") String menuCodes,
			@RequestParam("roleCode") String roleCode) {
		ArrayList<String> menuCodeList = new ArrayList<String>();
		String[] strs = menuCodes.split(",");
		for (String code : strs) {
			if (StringUtils.isNotBlank(code)) {
				menuCodeList.add(code);
			}
		}
		return roleService.saveAuthority(menuCodeList, roleCode, user.getAccount());
	}

	@PostMapping("/role/delete")
	@ResponseBody
	public ResultInfo deleteRole(User user, Role role) {
		role.setOperator(user.getAccount());
		return roleService.deleteRole(role);
	}

	@PostMapping("/role/disabledRole")
	@ResponseBody
	public ResultInfo disabledRole(User user, Role role) {
		role.setOperator(user.getAccount());
		role.setStatus(EnumCommonStatus.DISABLED.getCode());
		return roleService.changeStatus(role);
	}

	@PostMapping("/role/enabledRole")
	@ResponseBody
	public ResultInfo enabledRole(User user, Role role) {
		role.setOperator(user.getAccount());
		role.setStatus(EnumCommonStatus.NORMAL.getCode());
		return roleService.changeStatus(role);
	}
}
