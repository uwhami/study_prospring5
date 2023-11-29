package com.apress.prospring5.ch11.annotation_scheduled_task;

import com.apress.prospring5.ch11.annotation_scheduled_task.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class ScheduleTaskAnnotationDemo {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskAnnotationDemo.class);

    public static void main(String[] args) throws Exception{
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        System.in.read();
        ctx.close();

    }

}
