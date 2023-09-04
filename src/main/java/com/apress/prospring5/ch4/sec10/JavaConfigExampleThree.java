package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 4.10.2. 스프링 혼합 구성
 *
 * MessageRenderer 빈은 xml에서 정의하고,
 * MessageRenderer 빈에 있는 property 의존관계 에 해당하는 provider 빈은 애너테이션으로 정의한 bean을 가지고 왔다. *
 */
public class JavaConfigExampleThree {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ch4/sec10/app-context-02.xml");
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
