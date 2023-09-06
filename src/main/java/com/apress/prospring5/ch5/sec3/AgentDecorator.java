package com.apress.prospring5.ch5.sec3;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 5.3.3 AOP를 사용한 Hello,World
 *
 * 예제 5-2, 조인트포인트를 중심으로 실행되는 어라운드 어드바이스 구현체.
 *
 * MethodInterceptor 인터페이스 : 메서드 호출 조인포인트에 적용할 어라운드 어드바이스를 구현할 때 사용하는 표준 AOP 얼라이언스 인터페이스.
 * 어드바이스를 추가하는 메서드 호출을 나타내며,이 객체를 사용해 메서드 호출 시점을 제어할 수 있다.
 *
 * **어드바이스 : 특정 조인포인트에 실행되는 코드. 비포어드바이스와 애프터어디바이스 등 여러가지 존재.
 * **조인포인트 : 애플리케이션 실행 과정 내에 있는 명확한 지점.
 *
 */
public class AgentDecorator implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        /**
         * MethodInvocation 객체는 어드바이스를 추가하는 메서드 호출을 나타낸다.
         * 이 객체를 사용해 메서드 호출되는 시점을 제어할 수 있다.
         */
        System.out.print("James ");

        Object retVal = invocation.proceed();

        System.out.print("!");
        return retVal;
    }
}
