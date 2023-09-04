package com.apress.prospring5.ch4.sec12;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 4-86. 프로퍼티 파일 읽기 예제 테스트.
 *
 * application.home은 잘 읽어왔지만 user.home은 여전히 JVM 프로퍼티를 먼저 읽어온다.
 * XML 파일에서 local-override를 true로 주면, JVM 프로퍼티보다 application.properties에 파일에 있는 프로퍼티를 우선으로 읽어온다.
 */
public class PlaceHolderDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec12/app-context-xml.xml");
        ctx.refresh();

        AppProperty appProperty = ctx.getBean("appProperty", AppProperty.class);

        System.out.println("==========application.home: " + appProperty.getApplicationHome());
        System.out.println("==========user.home: " + appProperty.getUserHome());

        ctx.close();
    }
}
