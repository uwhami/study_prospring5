package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 예제 5-4. 비포 어드바이스를 사용한 SimpleBeforeAdvice 클래스
 *
 * Guitarist 클래스의 인스턴스에 SimpleBeforeAdvice 클래스의 인스턴스를 사용해 어드바이스를 적용함.
 * AOP 프레임워크는 조인포인트에서 메서드를 실행하기 전에 이 before() 메서드를 호출.
 */
public class SimpleBeforeAdvice implements MethodBeforeAdvice {

    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice()); //기본 포인트컷이므로 해당 코인트컷이 클래스의 모든 메서드에 적용된다.
        pf.setTarget(johnMayer);

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();

    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("==========Before '" + method.getName() + "', tune guitar.");
    }
}
