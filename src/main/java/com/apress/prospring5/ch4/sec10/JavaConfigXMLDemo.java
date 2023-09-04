package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 4.10 - ex 4-53 - 익숙한 XML을 사용한 메세지 제공자/렌더러 테스트
 *
 * 이제 xml이 아닌 자바 클래스를 이용해 메시지 제공자와 렌더러를 실행해 보자.
 */
public class JavaConfigXMLDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ch4/sec10/app-context-xml.xml");
        MessageRenderer renderer = ctx.getBean("messageRenderer",MessageRenderer.class);
        renderer.render();
    }
}
