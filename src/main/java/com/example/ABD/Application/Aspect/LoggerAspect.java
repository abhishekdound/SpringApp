package com.example.ABD.Application.Aspect;

import jakarta.persistence.Entity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private static final Logger logger= LoggerFactory.getLogger(LoggerAspect.class);

    @Before("execution(* com.example.ABD.Application.Service.PersonService.findAll(..))")
    public void before(JoinPoint jp){

        logger.info(jp.getSignature().getName());


    }

    @After("execution(* com.example.ABD.Application.Service.PersonService.findAll(..))")
    public void after(JoinPoint jp){

        System.out.println(jp.getSignature().getName());


    }

    @AfterReturning("execution(* com.example.ABD.Application.Service.PersonService.findAll(..))")
    public void afterReturning(JoinPoint jp){

        System.out.println(jp.getSignature().getName());


    }


    @AfterThrowing("execution(* com.example.ABD.Application.Service.PersonService.findAll(..))")
    public void afterThrowing(JoinPoint jp){

        System.out.println(jp.getSignature().getName());


    }

}
