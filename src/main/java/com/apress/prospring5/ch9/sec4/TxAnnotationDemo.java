package com.apress.prospring5.ch9.sec4;

import com.apress.prospring5.ch9.sec4.config.DataJpaConfig;
import com.apress.prospring5.ch9.sec4.config.ServicesConfig;
import com.apress.prospring5.ch9.sec4.entities.Singer;
import com.apress.prospring5.ch9.sec4.service.SingerService;
import com.apress.prospring5.ch9.sec4.service.SingerServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TxAnnotationDemo {

    public static void main(String[] args) {
//        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class, DataJpaConfig.class);
//        SingerService singerService = ctx.getBean(SingerService.class);

        /*9.4.3 트랜잭션 관리에 AOP 사용하기.*/
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch9/tx-declarative-app-context.xml");
        ctx.refresh();

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
