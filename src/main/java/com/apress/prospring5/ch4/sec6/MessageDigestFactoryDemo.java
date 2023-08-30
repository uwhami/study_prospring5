package com.apress.prospring5.ch4.sec6;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 예제 4-32 외부 팩터리 클래스 테스트.
 *
 * digesterF 빈은 MessageDigestFactory 클래스를 참조하는데,
 * MessageDigestFactory 클래스는 인터페이스로 FactoryBean을 상속받은 것이 아니라
 * 애트리뷰트로 factory-bean, factory-method를 지정했다.
 */
public class MessageDigestFactoryDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec6/app-context-xml.xml");
        ctx.refresh();

        MessageDigester digester = (MessageDigester) ctx.getBean("digesterF");
        digester.digest("Hello World!");
        ctx.close();
    }
}
