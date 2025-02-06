package com.example.ABD.Application.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParameterValidatingAspect {

    private static final Logger logger= LoggerFactory.getLogger(ParameterValidatingAspect.class);


    @Around("execution(* *.com.example.ABD.Application.Service.PersonService.findById(..))  && args(id)")
    public Object findById(ProceedingJoinPoint jp,int id) throws Throwable {

        if(id<0){

            id=-id;

        }

        jp.proceed(new Object[]{id});

        return jp;


    }



}
