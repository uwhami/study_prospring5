package com.apress.prospring5.ch4.sec14;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyBeansFromJava {

    public static void main(String... args) {
        ApplicationContext context = new GenericGroovyApplicationContext("classpath:beans.groovy");
        Singer singer = context.getBean("singer", Singer.class);
        System.out.println(singer);
    }
}
