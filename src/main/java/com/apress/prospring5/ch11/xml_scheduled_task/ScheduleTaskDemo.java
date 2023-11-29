package com.apress.prospring5.ch11.xml_scheduled_task;

import com.apress.prospring5.ch11.xml_scheduled_task.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskDemo {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskDemo.class);

    public static void main(String[] args) throws Exception{
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        CarService carService = ctx.getBean("carService", CarService.class);

//        while(!carService.isDone()){
//            logger.info("========== Waiting for finishing scheduling job...");
//            Thread.sleep(250);
//        }

        System.in.read();
        ctx.close();

    }

}
