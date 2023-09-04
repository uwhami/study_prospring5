package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import com.apress.prospring5.ch3.xml.ConfigurableMessageProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 4.10.1 - ex 4-56 : 가끔은 테스트 용도로 구성 클래스를 클래스 내에 정적 내부 클래스로 선언할 수 있다.
 * 클래스 내에 선언한 구성 클래스 코드이다.
 */
public class JavaConfigSimpleDemo {

    @Configuration
    static class AppConfigOne{
        @Bean
        public MessageProvider messageProvider(){
            System.out.println("==========Call MessageProvider Constructor.");
            return new ConfigurableMessageProvider();
        }

        @Bean
        public MessageRenderer messageRenderer(){
            MessageRenderer renderer = new StandardOutMessageRenderer();
            renderer.setMessageProvider(messageProvider());
            return renderer;
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfigOne.class);
        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }


}
