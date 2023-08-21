package com.apress.prospring5.ch3.xml;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Map;

public class BeanNameAliasing {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-02.xml");
        ctx.refresh();

        String s1 = (String) ctx.getBean("john");
        String s2 = (String) ctx.getBean("jon");
        String s3 = (String) ctx.getBean("johnny");
        String s4 = (String) ctx.getBean("jonathan");
        String s5 = (String) ctx.getBean("jim");
        String s6 = (String) ctx.getBean("ion");

        System.out.println(s1==s2);
        System.out.println(s2==s3);
        System.out.println(s3==s4);
        System.out.println(s4==s5);
        System.out.println(s5==s6);
        System.out.println(s6==s1);

        Map<String,String> bean = ctx.getBeansOfType(String.class);
        if(bean.size() == 1){
            System.out.println("Only One String Bean Exists");
        }

        //빈의 별칭 목록 가져옴
        ApplicationContext apc = new ClassPathXmlApplicationContext("spring/ch3/app-context-02.xml");
        for(String s : apc.getAliases("johnny")){
            System.out.println("name of beans : " + s);
        }
        System.out.println("ApplicationContext . getAliases : " +  apc.getAliases("johnny")[0]);



        ctx.close();
    }
}
