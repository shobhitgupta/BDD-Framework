package com.sapient.framework;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.api.Assert;

/**
 * @author sgu109
 *
 */

@Aspect
public class MyAspects {
	
	
	/**
	 * This method logs all the classes and methods being invoked. It uses AspectJ
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("execution(* *(..)) && !execution(* com.sapient.utils.Reporter.*(..))")
	public void loggerMethod(JoinPoint joinPoint)
			throws Throwable {

		Logger logger = Logger.getLogger("testExecutionLogger");
		MethodSignature signature = (MethodSignature) joinPoint
				.getSignature();
		String className = signature.getDeclaringTypeName();
		Method method = signature.getMethod();
		String methodName = method.getName();
		logger.info("Class: " + className + ", Method: " + methodName);
	
	}	
	

	
	/**
	 * Captures any exception and prints in the log file
	 * @param joinPoint
	 * @param e
	 * @throws Exception
	 */
	@AfterThrowing(pointcut="execution(* *(..))" , throwing = "e")
	public void errorCapture(JoinPoint joinPoint , Exception e) throws Exception
			 {

		Logger logger = Logger.getLogger("testExecutionLogger");
		MethodSignature signature = (MethodSignature) joinPoint
				.getSignature();
		String className = signature.getDeclaringTypeName();
		Method method = signature.getMethod();
		String methodName = method.getName();
		logger.error("Class: " + className + ", Method: " + methodName + " - Error:" + e.getMessage());
		
	}	
	
}