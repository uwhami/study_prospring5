package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch4.sec10.mixed.AppConfigFive;
import com.apress.prospring5.ch4.sec10.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ex 4-62. @ComponentScan 테스트
 *
 * AppConfigFive 는 스프링 혼합 구성으로 이루어져있다.
 * MessageRenderer는 애너테이션으로, p:provider은 xml로 정의했다.
 */
public class JavaConfigExampleTwo {

    public static void main(String[] args) {
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigFive.class);
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
