package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 5.4.8 애프터 리터닝 어드바이스 생성.
 *
 * 애프터 리터닝 어드바이스는 메서드 호출이 결과를 반환한 후에 실행되므로 전달한 인수를 변경할 수 없다.
 * 인수를 읽을 수는 있지만 실행 경로를 변경하거나 메서드 실행을 막을 수는 없다.
 *
 * 예상 불가능한 제약사항은!
 * 반환값을 수정할 수 없다.
 * 처리를 추가하는 것만 가능하다.
 * 스택에 보낼 예외를 던질 수는 있다.
 */
public class SimpleAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("==========After '" + method.getName() + "' put down guitar.");
    }

    public static void main(String[] args) {
        Guitarist target = new Guitarist();


        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvice(new SimpleAfterReturningAdvice());

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();

    }
}
