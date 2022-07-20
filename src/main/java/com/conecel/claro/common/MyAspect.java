//package com.conecel.claro.common;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//@Aspect
//@Component
//public class MyAspect {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Around("@annotation(com.conecel.claro.common.LogExecutionTime)")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        final StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start();
//
//        Object proceed = joinPoint.proceed();
//
//        stopWatch.stop();
//
//        logger.info("\"{}\" executed in {} ms", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
//
//        return proceed;
//    }
//
//}