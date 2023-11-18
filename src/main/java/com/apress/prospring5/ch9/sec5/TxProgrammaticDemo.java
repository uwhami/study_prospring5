package com.apress.prospring5.ch9.sec5;

import com.apress.prospring5.ch9.sec5.config.DataJpaConfig;
import com.apress.prospring5.ch9.sec5.config.ServicesConfig;
import com.apress.prospring5.ch9.sec5.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        System.out.println("========== Singer Count : " + singerService.contAll());

        ctx.close();
    }
}
