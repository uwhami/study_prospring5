package com.apress.prospring5.ch9;

import com.apress.prospring5.ch9.config.DataJpaConfig;
import com.apress.prospring5.ch9.config.ServicesConfig;
import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.service.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class TxAnnotationDemo {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(s -> System.out.println("==========" + s.toString()));

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John2");
        singer.setLastName("Mayer2");
        singerService.save(singer);
        System.out.println("========== Singer saved : " + singer.toString());

        System.out.println("========== COUNT : " + singerService.countAll());

        singers = singerService.findAll();
        singers.forEach(s -> System.out.println("==========" + s.toString()));

        ctx.close();
    }
}
