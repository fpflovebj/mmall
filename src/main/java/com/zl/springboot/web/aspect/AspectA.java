package com.zl.springboot.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Order(1)
public class AspectA {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * before
     * after
     * afterRetruning
     * afterThorwing
     * Around
     */
    //before
    @Before("execution(public * com.zl.springboot.security.controller.*.*(..))")
    public void before(JoinPoint jp){
        logger.info("before_"+jp.getTarget().getClass().getSimpleName());
    }

    //after
    @After("execution(public * com.zl.springboot.security.controller.*.*(..))")
    public void after(JoinPoint jp){
        logger.info("after");
    }

    //afterRetruning
    @AfterReturning(value = "execution(public * com.zl.springboot.security.controller.*.*(..))",returning = "res")
    public void afterReturning(JoinPoint jp ,Object res){
        logger.info("afterReturning_,返回值"+res);
    }

    //afterThrowing
    @AfterThrowing(value = "execution(public * com.zl.springboot.security.controller.*.*(..))",throwing = "e")
    public void afterReturning(JoinPoint jp ,Exception e){
        logger.info("接受exception"+e.getMessage());
    }
    //around
    @Around("execution(public * com.zl.springboot.security.controller.*.*(..))")
    public Object around(ProceedingJoinPoint pjp){
        logger.info("around in");
        try {
            Object  obj = pjp.proceed();
            logger.info("around out-"+obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}

