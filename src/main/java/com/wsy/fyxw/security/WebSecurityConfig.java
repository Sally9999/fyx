package com.wsy.fyxw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.security.handler.LoginFailureHandler;
import com.wsy.fyxw.security.handler.LoginSuccessHandler;
import com.wsy.fyxw.security.provider.LoginAuthenticationProvider;
import com.wsy.fyxw.security.util.AuthenticationUtil;
import com.wsy.fyxw.service.UserService;

@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	private LoginAuthenticationProvider loginAuthenticationProvider;
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationUtil authenticationUtil;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		loginAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		loginAuthenticationProvider.setHideUserNotFoundExceptions(false);
		loginAuthenticationProvider.setUserDetailsService(userService);
		auth.authenticationProvider(loginAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()// 表单登录
				.loginPage("/login")// 请求控制处理类
				.loginProcessingUrl("/authentication/form")// 登录POST请求URL
				.successHandler(loginSuccessHandler)// 登录成功处理器
				.failureHandler(loginFailureHandler)// 登录失败处理器
				.and().logout().logoutUrl("/logout")// 指定退出登录URL
				.deleteCookies("JSESSIONID")// 删除指定的cookies
//				.and().rememberMe().tokenValiditySeconds(604800)// 记住我功能，cookies有限期是一周
//				.rememberMeParameter("remember-me")// 登录时是否激活记住我功能的参数名字，在登录页面有展示
//				.rememberMeCookieName("fyxw")// cookies的名字，登录后可以通过浏览器查看cookies名字
				.and().csrf().disable();// 取消跨站访问防护
		authenticationUtil.putAuthorizeRequests(http.authorizeRequests());
	}
}
