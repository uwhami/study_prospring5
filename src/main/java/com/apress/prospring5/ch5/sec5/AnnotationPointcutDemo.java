package com.apress.prospring5.ch5.sec5;

import com.apress.prospring5.ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

/**
 * 5.5.9 애너테이션 매칭 포인트컷 생성.
 *
 * 5-40. 애너테이션을 활용한 포인트컷 테스트.
 * AnnotationMatchingPointcut : 지정한 애너테이션이 적용된 모든 메서드에 어드바이스를 적용함.
 */
public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        Guitarist2 johnMayer = new Guitarist2();

        AnnotationMatchingPointcut pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        Guitarist2 proxy = (Guitarist2) pf.getProxy();
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}
