package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch5.sec4.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 5.5.4 StaticMethodMatcherPointcut을 사용해 정적 포인트컷 생성하기.
 *
 * 예제 5-27 정적 포인트컷을 사용해 DefaultPointcutAdvisor 인스턴스를 생성하는 예제.
 */
public class StaticPointcutDemo {

    public static void main(String[] args) {
        GoodGuitarist dj = new GoodGuitarist();
        GreatGuitarist jh = new GreatGuitarist();

        Singer proxyOne;
        Singer proxyTwo;

        Pointcut pc = new SimpleStaticPointcut();   //StaticMethodMatcherPointcut를 상속받아 matches메서드와 getClassFilter를 구현한 클래스.
        Advice advice = new SimpleAdvice(); //MethodInterceptor을 상속받아 invoke 메서드를 구현한 클래스.
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(dj);
        proxyOne = (Singer) pf.getProxy();

        pf = new ProxyFactory();
        pf.addAdvisor(advisor);
        pf.setTarget(jh);
        proxyTwo = (Singer) pf.getProxy();

        proxyOne.sing();
        proxyTwo.sing();

    }
}
