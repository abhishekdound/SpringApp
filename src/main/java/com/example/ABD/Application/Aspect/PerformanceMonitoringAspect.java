package com.example.ABD.Application.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoringAspect {

    private static final Logger logger= LoggerFactory.getLogger(PerformanceMonitoringAspect.class);

    @Around("execution(* com.example.ABD.Application.Service.PersonService.findAll(..))")
    public Object performance(ProceedingJoinPoint pj) throws Throwable {

        long start=System.currentTimeMillis();

        Object ob=pj.proceed();

        long end=System.currentTimeMillis();

        logger.info("Performance time is " + (start-end));

        return ob;


    }


}
