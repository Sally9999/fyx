package com.wsy.fyxw.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.query.MenuGroupQuery;
import com.wsy.fyxw.query.MenuQuery;
import com.wsy.fyxw.service.MenuService;

@Controller
public class MenuAction extends BaseAction {

	@Autowired
	private MenuService menuService;

	/**
	 * 菜单列表页
	 * 
	 * @param groupCode
	 * @param groupName
	 * @param model
	 * @return
	 */
	@GetMapping("/menu/menuManagement")
	public String menuPage(String groupCode, String groupName, Model model) {
		model.addAttribute("groupCode", groupCode);
		if (StringUtils.isBlank(groupName)) {
			groupName = menuService.getMenuGroupByCode(groupCode).getGroupName();
		}
		model.addAttribute("groupName", groupName);
		return "menu/menuManagement";
	}

	/**
	 * 菜单设置页
	 * 
	 * @param menuCode
	 * @param model
	 * @return
	 */
	@GetMapping("/menu/menuSetting")
	public String menuDetailPage(String menuCode, Model model) {
		// 根据menuCode查询菜单信息
		Menu menu = menuService.getMenuByCode(menuCode);
		// 查菜单组列表
		MenuGroupQuery query = new MenuGroupQuery();
		query.setPageSize(Integer.MAX_VALUE);
		query = menuService.getMenuGroupPage(query);
		model.addAttribute("menu", menu);
		model.addAttribute("groupList", query.getResultItems());
		return "menu/menuDetail";
	}

	/**
	 * 菜单新增页
	 * 
	 * @return
	 */
	@GetMapping("/menu/menuCreation")
	public String menuAddPage(String groupCode, Model model) {
		// 查菜单组列表
		MenuGroupQuery query = new MenuGroupQuery();
		query.setPageSize(Integer.MAX_VALUE);
		query = menuService.getMenuGroupPage(query);
		model.addAttribute("groupCode", groupCode);
		model.addAttribute("groupList", query.getResultItems());
		return "menu/menuAdd";
	}
	
	/**
	 * 查询菜单分页数据
	 * 
	 * @param query
	 * @param model
	 * @return
	 */
	@PostMapping("/menu/getPage")
	public String getMenuByPage(MenuQuery query, Model model) {
		query = menuService.getMenuPage(query);
		model.addAttribute("query", query);
		return "menu/menuManagement::menuList";

	}

	/**
	 * 更新或创建菜单
	 * @param user
	 * @param menu
	 * @return
	 */
	@PostMapping("/menu/submit")
	@ResponseBody
	public ResultInfo saveMenu(User user, Menu menu) {
		menu.setOperator(user.getAccount());
		return menuService.saveMenu(menu);
	}

	/**
	 * 删除菜单
	 * @param user
	 * @param menu
	 * @return
	 */
	@PostMapping("/menu/delete")
	@ResponseBody
	public ResultInfo deleteMenu(User user, Menu menu) {
		menu.setOperator(user.getAccount());
		return menuService.deleteMenu(menu);
	}
}
