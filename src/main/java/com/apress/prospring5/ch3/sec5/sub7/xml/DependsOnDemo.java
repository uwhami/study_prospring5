package com.apress.prospring5.ch3.sec5.sub7.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DependsOnDemo {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-01.xml");
        ctx.refresh();

        Singer johnMayer = ctx.getBean("johnMayer", Singer.class);
        johnMayer.sing();


        Singer johnMayer2 = ctx.getBean("johnMayer2", Singer.class);
        System.out.println("==========Are they same ? : " + (johnMayer == johnMayer2)); //app-context-01 설정에 따라 true false 다름.

        ctx.close();
    }
}
