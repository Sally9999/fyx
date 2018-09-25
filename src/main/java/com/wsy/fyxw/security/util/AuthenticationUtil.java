package com.wsy.fyxw.security.util;

import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.Menu;
import com.wsy.fyxw.service.MenuService;

@Component("authenticationUtil")
public class AuthenticationUtil {

	@Autowired
	private MenuService menuService;

	public void putAuthorizeRequests(
			ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
		registry.antMatchers("/login", "/regist", "/createAccount", "/js/**", "/css/**", "/img/**", "/fonts/**")
				.permitAll();// 请求不需验证
		ArrayList<Menu> list = menuService.getAuthentication();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Menu menu : list) {
				registry.antMatchers(menu.getUrl()).hasRole(menu.getRoleCode());
			}
		}
		registry.anyRequest().authenticated();// 其余需要验证
	};
}
