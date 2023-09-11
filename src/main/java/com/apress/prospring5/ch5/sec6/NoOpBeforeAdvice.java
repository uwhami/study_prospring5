package com.apress.prospring5.ch5.sec6;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 예제 5-44. 아무 작업도 수행하지 않는 간단한 비포 어드바이스가 포함된 NoOpBeforeAdvice 클래스
 */
public class NoOpBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // no-op
    }
}
