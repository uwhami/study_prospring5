package com.apress.prospring5.ch4.sec8;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 4.8.4 애플리케이션 이벤트
 *
 * 웹 애플리케이션에서는 많은 클래스가 스프링 프레임워크 클래스에서 파생되므로 ApplicationContext 에 접근할 수 있으나
 * 독립형 애플리케이션에서는 이벤트 발생 빈이 ApplicationContextAware를 구현하면 이벤트를 발생할 수 있다.
 */
public class Publisher implements ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    public void publish(String message){
        ctx.publishEvent(new MessageEvent(this, message));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ch4/sec8/app-context-xml.xml");
        Publisher pub = (Publisher)ctx.getBean("publisher");
        pub.publish("==========I am sending SOS to world");
        pub.publish("==========...I hope someone sees...");
        pub.publish("==========...this message in a bottle..");
    }
}
