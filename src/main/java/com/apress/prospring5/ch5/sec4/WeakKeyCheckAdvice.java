package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static com.apress.prospring5.ch5.sec4.KeyGenerator.WEAK_KEY;

public class WeakKeyCheckAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if((target instanceof KeyGenerator) && ("getKey".equals(method.getName()))){
            long key = ((Long) returnValue).longValue();
            if(key == WEAK_KEY){
                throw new SecurityException("==========Key generator made WEAK_KEY, Regenerate Key.");
            }
        }
    }

}
