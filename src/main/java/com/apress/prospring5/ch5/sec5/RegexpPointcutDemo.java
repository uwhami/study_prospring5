package com.apress.prospring5.ch5.sec5;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

/**
 * 5.5.7 정규 표현식으로 포인트컷 만들기.
 * 예제 5-35. 포인트컷으로 정규식을 사용하는 클래스.
 *
 * 패턴에서, 메서드 이름을 매칭할 때 스프링은 패키지와 클래스 이름이 포함된 전체 메서드 이름과 매칭한다.
 * 따라서, sing1()에 대해 스프링은 com.apress.prospring5...과 매칭하기 때문에 패턴 앞부분에 .*을 사용한다.
 * 이 패턴은 패키지에 있는 클래스와 메서드의 이름을 정확히 알 필요 없이 주어진 패키지 내의 모든 매서드를 매칭할 수 있다.
 */
public class RegexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*sing.*");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());   //SimpleAdvice : MethodInterceptor 를 상속받아 invoke를 구현한 클래스

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
        proxy.sing2();
        proxy.rest();

    }
}
