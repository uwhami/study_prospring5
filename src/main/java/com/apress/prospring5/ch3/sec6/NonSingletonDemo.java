package com.apress.prospring5.ch3.sec6;


import com.apress.prospring5.ch3.sec6.annotated.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 3-105. 프로토타입 스코프를 지정하면 스프링은 매번 새로운 인스턴스를 생성하고, 둘은 값도 다르게 인식함.
 */
public class NonSingletonDemo {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-xml.xml");
        ctx.refresh();

        Singer singer1 = ctx.getBean("nonSingleton", Singer.class);
        Singer singer2 = ctx.getBean("nonSingleton", Singer.class);

        System.out.println("==========Are they the same identifier? : " + (singer1 == singer2));
        System.out.println("==========Does they have same values? : " + (singer1.equals(singer2)));
        System.out.println(singer1);
        System.out.println(singer2);
    }


}
