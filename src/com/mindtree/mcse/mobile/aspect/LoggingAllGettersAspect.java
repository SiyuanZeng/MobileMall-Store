package com.mindtree.mcse.mobile.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAllGettersAspect {
	
	public Object logAllGettersAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking getter");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking getter. Return value="+value);
		return value;
	}
	
}
