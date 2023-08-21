package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Map;

/*
 * 3-85.
 */
public class BeanNamingTest {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-01.xml");
        ctx.refresh();

        //getBeansOfType(String.class)는 String 타입의 모든 빈과 ApplicationContext 내에 존재 하는 ID가 담긴 Map을 얻는데 사용.
        Map<String,String> beans = ctx.getBeansOfType(String.class);
        beans.entrySet().stream().forEach(b -> System.out.println(b.getKey()));
        ctx.close();
    }
}
