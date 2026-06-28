package com.anjali.smartproductcatalog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductLoggingAspect {

    @Before("execution(* com.anjali.smartproductcatalog.service.*.*(..))")
    public void beforeMethod(JoinPoint jp) {
        System.out.println("➡ Entering: " + jp.getSignature());
    }

    @AfterReturning(
            pointcut = "execution(* com.anjali.smartproductcatalog.service.*.*(..))",
            returning = "result"
    )
    public void afterMethod(JoinPoint joinPoint, Object result) {
        System.out.println("Exiting: " + joinPoint.getSignature());
        System.out.println("Result: " + result);
    }

    @Around("execution(* com.anjali.smartproductcatalog.service.*.*(..))")
    public Object calculateTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start) + " ms");

        return result;
    }
}