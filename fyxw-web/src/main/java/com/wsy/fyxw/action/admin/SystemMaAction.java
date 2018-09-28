package com.wsy.fyxw.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.SystemConfig;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumCommonStatus;
import com.wsy.fyxw.query.SystemConfigQuery;
import com.wsy.fyxw.service.SystemConfigService;

/**
 * 系统管理
 * 
 * @author Sally
 *
 */
@Controller
public class SystemMaAction extends BaseAction {

	@Autowired
	private SystemConfigService systemConfigService;

	@GetMapping("/system/systemManagement")
	public String systemConfigListPage() {
		return "system/systemManagement";
	}

	@PostMapping("/system/getPage")
	@ResponseBody
	public JSONObject getUserListByPage(SystemConfigQuery query, Model model) {
		JSONObject result = new JSONObject();
		query = systemConfigService.getConfigPage(query);
		model.addAttribute("query", query);
		result.put("total", query.getTotalRecord());
		result.put("rows", query.getResultItems());
		return result;
	}

	@PostMapping("/system/configSave")
	@ResponseBody
	public ResultInfo saveConfig(User user, SystemConfig config) {
		config.setOperator(user.getAccount());
		return systemConfigService.save(config);
	}

	@PostMapping("/system/disabledConfig")
	@ResponseBody
	public ResultInfo disabledConfig(User user, SystemConfig config) {
		config.setOperator(user.getAccount());
		config.setStatus(EnumCommonStatus.DISABLED.getCode());
		return systemConfigService.changeStatus(config);
	}

	@PostMapping("/system/enabledConfig")
	@ResponseBody
	public ResultInfo enabledConfig(User user, SystemConfig config) {
		config.setOperator(user.getAccount());
		config.setStatus(EnumCommonStatus.NORMAL.getCode());
		return systemConfigService.changeStatus(config);
	}
}
