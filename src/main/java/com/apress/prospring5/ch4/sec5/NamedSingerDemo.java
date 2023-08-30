package com.apress.prospring5.ch4.sec5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class NamedSingerDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec5/app-context-xml.xml");
        ctx.refresh();

        NamedSinger bean = (NamedSinger) ctx.getBean("johnMayer");
        bean.sing();

        ctx.close();
    }
}
