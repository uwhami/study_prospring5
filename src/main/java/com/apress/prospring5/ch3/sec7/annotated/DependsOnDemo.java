package com.apress.prospring5.ch3.sec7.annotated;

import com.apress.prospring5.ch3.sec7.annotated.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DependsOnDemo {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-02.xml");
        ctx.refresh();

        Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
        johnMayer.sing();

        ctx.close();
    }
}
