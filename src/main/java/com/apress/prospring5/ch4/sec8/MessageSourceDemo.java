package com.apress.prospring5.ch4.sec8;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

/**
 *  4.8.1.1 MessageSource를 사용한 국제화
 *
 *  ApplicationContext가 제공하는 MessageSource 기능을 활용하기.
 *  Xml 파일에서 messageSource 타입 빈을 구성에 정의하고 해당 빈을 가져와 내장하면
 *  애플리케이션은 ApplicationContext를 사용해 메세지에 접근할 수 있다.
 *
 *  불필요하게 ApplicationContext와 결합시킥 때문에 스프링 MVC 프레임워크를 사용해 웹 애플리케이션을 만들 때만 메세지를 가지고올때 ApplicationContext를 사용해야 한다.
 *  ApplicationContext를 MessageSource로 사용하는 이유는 4.8.1.3 을 보라.(p.265)
 */
public class MessageSourceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec8/app-context-xml.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale korean = new Locale("ko","KR");

        System.out.println(ctx.getMessage("msg",null,english));
        System.out.println(ctx.getMessage("msg",null,korean));

        System.out.println(ctx.getMessage("name", new Object[]{"john","Mayer"}, english));
        System.out.println(ctx.getMessage("name", new Object[]{"john","Mayer"}, korean));

        ctx.close();
    }
}
