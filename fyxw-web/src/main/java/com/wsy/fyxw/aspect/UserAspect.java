package com.wsy.fyxw.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.wsy.fyxw.domain.User;

@Component
@Aspect
public class UserAspect {

	Logger logger = LoggerFactory.getLogger(getClass());

	private final String point = "execution(public * com.wsy.fyxw.action..*.*(com.wsy.fyxw.domain.User,..))";

	/**
	 * 拦截以user为第一个参数的action方法，并设置user对象
	 * 
	 * @param point
	 * @return
	 */
	@Around(point)
	public Object around(ProceedingJoinPoint point) {
		try {
			String className = point.getTarget().getClass().getName();
			String methodName = point.getSignature().getName();
			logger.info(">>>>>>>>>>>>>>>> {}.{}进入UserAspect", className, methodName);
			Object[] args = point.getArgs();
			args[0] = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return point.proceed(args);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
