package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch2.common.Guitar;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 5.5.10 편리한 Advisor 구현체.
 *
 * NamePointcutDemo 클래스에서  DefaultPointcutAdvisor 와 함께 NameMatchMethodPointcut 를 사용하는 대신
 * NameMatchMethodPointcutAdvisor 을 사용할 수 있다.
 * NameMatchMethodPointcutAdvisor가 Advisor이자 포인트컷 역할을 한다.
 */
public class NamePointcutUsingAdvisor {
    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        advisor.addMethodName("sing");
        advisor.addMethodName("rest");

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();

    }
}
