package com.apress.prospring5.ch5.sec5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 예제 5-30. 동적 포인트컷 실행 클래스.
 *
 * 처음 두번의 foo() 메서드 호출에만 어드바이스가 적용되었습니다.
 * bar() 메서드에 대한 정적 검사로 인해 bar() 메서드 호출은 동적 검사를 전혀 받지 않았다.
 * foo() 메서드는 모든 메서드를 검사하는 초기화 단계와 메서드가 처음 호출되는 단계에서 두 번의 정적 검사를 받았다.
 *
 * 동적 포인트컷은 정적 포인트컷 보다 더 큰 유연성을 제공하지만
 * 런타임의 성능 오버헤드를 고려하여 꼭 필요할 때만 동적 포인트컷을 사용해야 한다.
 */
public class DynamicPointcutDemo {
    public static void main(String[] args) {
        SampleBean target = new SampleBean();

        //SimpleDynamicPointcut : 정적 검사와 동적 검사를 구현한 클래스
        //SimpleAdvice 클래스 : MethodInterceptor을 상속받아 invoke 메서드를 구현한 클래스.
        Advisor advisor = new DefaultPointcutAdvisor(
                new SimpleDynamicPointcut(), new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        SampleBean proxy = (SampleBean) pf.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(100);

        System.out.println("==========");

        proxy.bar();
        proxy.bar();
        proxy.bar();

        proxy.foo(50);

        proxy.bar();
    }
}
