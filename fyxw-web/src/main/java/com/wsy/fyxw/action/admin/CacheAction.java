package com.wsy.fyxw.action.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsy.fyxw.action.BaseAction;
import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.ResultInfo;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumCommonResult;
import com.wsy.fyxw.service.MenuService;
import com.wsy.fyxw.util.ResultUtil;

@RestController
public class CacheAction extends BaseAction {

	@Autowired
	private MenuService menuService;

	private final String ROLE_PREFIX = "ROLE_";

	@SuppressWarnings("unchecked")
	@PostMapping("/system/cache/refreshMenu")
	public ResultInfo refreshMenu(User user, HttpServletRequest httpServletRequest) {
		ResultInfo result = ResultUtil.setResultInfo(EnumCommonResult.FAILED, null);
		try {
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
			ArrayList<String> roleCodeList = new ArrayList<String>();
			if (CollectionUtils.isNotEmpty(authorities)) {
				for (GrantedAuthority authority : authorities) {
					if (StringUtils.startsWith(authority.getAuthority(), ROLE_PREFIX)) {
						roleCodeList.add(authority.getAuthority().replace(ROLE_PREFIX, ""));
					}
				}
			}
			// 获取菜单信息
			ArrayList<MenuGroup> list = menuService.getAuthorityByRoleList(roleCodeList);
			httpServletRequest.getSession().setAttribute("CACHE_USER_MENU", list);
			result = ResultUtil.setResultInfo(EnumCommonResult.SUCCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
