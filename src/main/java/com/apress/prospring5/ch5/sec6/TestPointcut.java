package com.apress.prospring5.ch5.sec6;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 예제 5-43. 어드바이스를 적용하는 메서드를 정적으로 검사하는 TestPointcut 클래스.
 */
public class TestPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("advise".equals(method.getName()));
    }
}
