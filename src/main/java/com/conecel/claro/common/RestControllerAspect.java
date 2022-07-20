//package com.conecel.claro.common;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import lombok.extern.log4j.Log4j2;
//
//@Aspect
//@Log4j2
//@Component
//public class RestControllerAspect {
//
//	// AOP expression for which methods shall be intercepted
//	@Around("execution(* com.conecel.claro..*(..)))")
//	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//		// Get intercepted method details
//		String className = methodSignature.getDeclaringType().getSimpleName();
//		String methodName = methodSignature.getName();
//
//		final StopWatch stopWatch = new StopWatch();
//
//		// Measure method execution time
//		stopWatch.start();
//		Object result = proceedingJoinPoint.proceed();
//		stopWatch.stop();
//
//		// Log method execution time
//		log.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
//
//		return result;
//	}
//}