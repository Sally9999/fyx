package com.wsy.fyxw.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsy.fyxw.domain.MenuGroup;
import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumLogType;
import com.wsy.fyxw.enums.EnumUserResult;
import com.wsy.fyxw.service.MenuService;
import com.wsy.fyxw.util.LogUtil;

@Component("loginSuccessHandler")
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MenuService menuService;
	@Autowired
	protected LogUtil logUtil;
	private final String ROLE_PREFIX = "ROLE_";

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		logger.info("用户登录成功");
		httpServletResponse.setContentType("application/json;charset=UTF-8");
		// 将authentication转成string类型json字符串
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
		// 获取角色code
		User user = (User) authentication.getPrincipal();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
		ArrayList<String> roleCodeList = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(authorities)) {
			for (GrantedAuthority authority : authorities) {
				if (StringUtils.startsWith(authority.getAuthority(), ROLE_PREFIX)) {
					roleCodeList.add(authority.getAuthority().replace(ROLE_PREFIX, ""));
				}
			}
		}
		if (CollectionUtils.isNotEmpty(roleCodeList)) {
			// 获取菜单信息
			ArrayList<MenuGroup> list = menuService.getAuthorityByRoleList(roleCodeList);
			httpServletRequest.getSession().setAttribute("CACHE_USER_MENU", list);
		}
		// 查询登录日志确认是否第一次登录
		Long count = logUtil.getLogCount(user.getAccount(), EnumLogType.LOGIN.getCode(),
				EnumUserResult.SUCCESS.getCode());
		if (count == 1) {
			redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,
					"/user/nicknameSetting?operate=first");
			super.setRedirectStrategy(redirectStrategy);
		}
		super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
	}

}
