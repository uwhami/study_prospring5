package com.apress.prospring5.ch3.config;

import com.apress.prospring5.ch3.DemoBean;
import com.apress.prospring5.ch3.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

public class LookupConfigDemo {

    @Configuration
    @ComponentScan(basePackages = {"com.apress.prospring5.ch3.annotated"})
    public static class LookupConfig{}

    public static void main(String... args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(LookupConfig.class);
        DemoBean abstractBean = ctx.getBean("abstractLookupBean", DemoBean.class);
        DemoBean standardBean = ctx.getBean("standardLookupBean", DemoBean.class);

        displayInfo("abstractLookupBean", abstractBean);
        displayInfo("standardLookupBean", standardBean);
        ctx.close();
    }


    public static void displayInfo(String beanName, DemoBean bean){
        Singer singer1 = bean.getMySinger();
        Singer singer2 = bean.getMySinger();

        System.out.println("[" + beanName + "] : Are Singer instances same ? " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for(int x=0; x<100000; x++){
            Singer singer = bean.getMySinger();
            singer.sing();
        }

        stopWatch.stop();

        System.out.println("Time taken to get 100000 times : " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
