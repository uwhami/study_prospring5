package com.apress.prospring5.ch5.sec5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 예제 5-26. 메서드 호출 전후로 메세지를 간단히 작성하는 SimpleAdvice 클래스.
 */
public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("==========>> Invoking " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("==========>> Done\n");
        return retVal;
    }
}
