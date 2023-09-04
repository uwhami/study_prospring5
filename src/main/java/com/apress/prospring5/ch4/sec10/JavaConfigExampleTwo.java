package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch4.sec10.multiple.AppConfigThree;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ex 4-62. @ComponentScan 테스트
 */
public class JavaConfigExampleTwo {

    public static void main(String[] args) {
        //ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigTwo.class);
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigThree.class);
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
