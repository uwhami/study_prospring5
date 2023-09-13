package com.apress.prospring5.ch5.sec8;

import com.apress.prospring5.ch2.common.Contact;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 5.8.2.4 한데 모으기.
 *
 * 믹스인 클래스와 Advisor 클래스가 생겼으므로 수정 검사 프레임워크를 테스트 할 수 있다.
 */
public class IntroductionDemo {
    public static void main(String[] args) {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);
        //optimize 플래그를 true로 설정해 CGLIB 프록시를 강제로 사용하도록 한다.
        //그 이유는 JDK 프록시를 사용해 믹스인을 인트로듀스할 때 생성된 프록시가 객체클래스(Contact)의 인스턴스가 아니기 때문.
        //이 프록시는 원래 클래스가 아닌 믹스인 인터페이스만을 구현한다.
        //CGLIB 프록시를 사용하면 믹스인 인터페이스와 함께 원래 클래스를 구현한다.

        Contact proxy = (Contact) pf.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("==========Is Contact?: " + (proxy instanceof Contact));
        System.out.println("==========Is IsModified?: " + (proxy instanceof IsModified));

        System.out.println("==========Has been modified?: " + proxyInterface.isModified());
        proxy.setName("Eric Clapton");

        System.out.println("==========Has been modified?: " + proxyInterface.isModified());

    }
}
