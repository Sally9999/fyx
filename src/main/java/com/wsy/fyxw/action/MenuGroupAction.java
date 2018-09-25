package com.wsy.fyxw.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.service.MenuService;

@Controller
public class MenuGroupAction extends BaseAction {

	@Autowired
	private MenuService menuService;

	/**
	 * 菜单组管理页
	 * 
	 * @return
	 */
	@GetMapping("/menuGroup/menuGroupManagement")
	public String menuGroupPage() {
		return "menuGroup/menuGroupManagement";
	}

	/**
	 * 菜单组新增页
	 * 
	 * @return
	 */
	@GetMapping("/menuGroup/groupCreation")
	public String menuGroupAddPage() {
		return "menuGroup/groupAdd";
	}

	/**
	 * 菜单组设置页
	 * 
	 * @param groupCode
	 * @param model
	 * @return
	 */
	@GetMapping("/menuGroup/groupSetting")
	public String menuGroupDetailPage(String groupCode, Model model) {
		// 根据groupCode查询菜单组信息
		MenuGroup group = menuService.getMenuGroupByCode(groupCode);
		model.addAttribute("group", group);
		return "menuGroup/groupDetail";
	}
	
	/**
	 * 查询菜单组分页数据
	 * 
	 * @param query
	 * @param model
	 * @return
	 */
	@PostMapping("/menuGroup/getPage")
	public String getMenuGroupByPage(MenuGroupQuery query, Model model) {
		query = menuService.getMenuGroupPage(query);
		model.addAttribute("query", query);
		return "menuGroup/menuGroupManagement::groupList";

	}

	/**
	 * 更新或创建菜单组
	 * @param user
	 * @param group
	 * @return
	 */
	@PostMapping("/menuGroup/submit")
	@ResponseBody
	public ResultInfo saveMenuGroup(User user, MenuGroup group) {
		group.setOperator(user.getAccount());
		return menuService.saveMenuGroup(group);
	}


	/**
	 * 删除菜单组
	 * @param user
	 * @param group
	 * @return
	 */
	@PostMapping("/menuGroup/delete")
	@ResponseBody
	public ResultInfo deleteMenuGroup(User user, MenuGroup group) {
		group.setOperator(user.getAccount());
		return menuService.deleteMenuGroup(group);
	}
}
