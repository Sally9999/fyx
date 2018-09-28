package com.wsy.fyxw.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.User;
import com.wsy.fyxw.enums.EnumUserResult;
import com.wsy.fyxw.enums.EnumUserStatus;
import com.wsy.fyxw.service.UserService;
import com.wsy.fyxw.util.LogUtil;
import com.wsy.fyxw.util.SystemConfigUtil;

@Component("authenticationProvider")
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired(required=false)
	private UserService userDetailsService;

	@Autowired
	private LogUtil logUtil;
	@Autowired
	private SystemConfigUtil systemConfigUtil;

	protected UserService getUserDetailsService() {
		return userDetailsService;
	}

	protected void setUserDetailsService(UserService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {

		try {
			Authentication auth = super.authenticate(authentication);
			User user = (User) auth.getPrincipal();
			// 如果曾有密码错误，更新出错次数为0
			if (user.getLoginFailedTimes() > 0) {
				user.setLoginFailedTimes(0);
				userDetailsService.updateUser(user);
			}
			// 记录登录日志
			logUtil.writeLoginLog(user, EnumUserResult.SUCCESS);
			return auth;

		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException(EnumUserResult.USER_NOT_FIND.getValue());
		} catch (BadCredentialsException e) {
			throw e;
		} catch (LockedException e) {
			throw new LockedException("密码都记不住，不跟你玩了...");
		} catch (DisabledException e) {
			throw new DisabledException("你...被踢了...");
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials", EnumUserResult.PWD_NULL.getValue()));
		}

		String presentedPassword = authentication.getCredentials().toString();
		PasswordEncoder passwordEncoder = getPasswordEncoder();
		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");
			// 查询最大允许出错次数
			int maxFailedTimes = systemConfigUtil.getMaxLoginFailedTimes();
			// 超过最大值，更新用户状态为锁定
			User user = (User) userDetails;
			int failedTimes = user.getLoginFailedTimes() + 1;
			int leftTimes = maxFailedTimes - failedTimes;
			String message = EnumUserResult.PWD_NOT_MATCH.getValue() + ",再给你" + leftTimes + "次机会";
			if (leftTimes <= 0) {
				user.setStatus(EnumUserStatus.LOCKED.getCode());
				message = EnumUserResult.PWD_NOT_MATCH.getValue() + ",记性不好就算了吧";
			}
			user.setLoginFailedTimes(failedTimes);
			userDetailsService.updateUser(user);
			logUtil.writeLoginLog(user, EnumUserResult.PWD_NOT_MATCH);
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", message));
		}
	}
	
	protected void doAfterPropertiesSet() throws Exception {
		
	}
}
