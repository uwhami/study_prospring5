package com.apress.prospring5.ch5.sec5;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 5.5.8 AspectJ 포인트컷 표현식을 사용한 포인트컷 생성.
 *
 * 스프링에서 @AspectJ 애너테이션 방식 AOP를 지원 기능을 사용할 때는 AspectJ의 포인트컷 언어를 사용해야 한다.
 * 따라서 표현식 언어를 사용해 포인트컷을 선언할 때에는 AspectJ 표현식을 사용하는 것이 가장 좋은 방법이다.
 *
 * JDK 정규 표현식으로 구현된 동일한 기능을 AspectJ 표현식에서 사용하여 구현할 수 있다.
 * "execution(* sing*(..))" 표현식은 인수, 변환 타입에 상관없이 sing으로 시작하는 모든 메서드에 어드바이스를 적용한다는 의미.
 */
public class AspectjexpPointcutDemo {
    public static void main(String[] args) {
        Guitarist johnMayer = new Guitarist();

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* sing*(..))"); //** 책에는 sing*(...)로 되어있으나 이렇게 하면 오류남.
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
