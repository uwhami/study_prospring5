package com.apress.prospring5.ch5.sec12;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 5.12 AspectJ 연동
 *
 * 5.12.2 싱글턴 애스펙트 사용하기.
 * 예제 5-90 AspectJ 테스트 예제.
 *
 * 실행 안됨.
 * app-config-xml도 아니고 app-context-xml인데 처음에 책에서 오타로 파일 만들어서 그대로 하게됨 ㅠ
 * 이 책에서는 AspectJ 애스팩트를 애플리케이션에 위빙하는 방법은 다루지 않음.
 */
public class AspectJDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch5/sec12/app-config-xml.xml");
        ctx.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
