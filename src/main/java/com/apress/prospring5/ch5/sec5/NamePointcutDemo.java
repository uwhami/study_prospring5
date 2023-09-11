package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 5.5.6 단순한 이름 매칭 포인트컷 사용하기.
 * 예세 5-33. NameMatchMethodPointcur 사용하기
 *
 * *여기서는 포인트컷 클래스를 생성하지 않음.
 * NameMatchMethodPointcut의 인스턴스를 간단하게 생성하면 포인트컷을 적용할 수 있다.
 */
public class NamePointcutDemo {

    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("sing");
        pc.addMethodName("rest");

        //SimpleAdvice : MethodInterceptor 를 상속받아 invoke를 구현한 클래스.
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
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
