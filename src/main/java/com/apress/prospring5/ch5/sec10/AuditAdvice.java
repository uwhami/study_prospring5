package com.apress.prospring5.ch5.sec10;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("==========Executing: "
                + method);
    }

//    public void simpleBeforeAdvice(JoinPoint joinPoint){
//        System.out.println("==========Executing: "
//                + joinPoint.getSignature().getDeclaringTypeName()
//                + " "
//                + joinPoint.getSignature().getName());
//    }
}
