package com.apress.prospring5.ch5.sec10;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 5.10 AOP 프레임워크 서비스.
 *
 * 5.10.2.1 ProxyFactoryBean 활용하기.
 * 의존선 주입으로 실행되는 AOP.
 */
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/ch5/sec10/app-context-xml.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("==========Documentarist One >>");
        documentaristOne.execute();

        System.out.println("==========Documentarist Two >>");
        documentaristTwo.execute();
    }
}
