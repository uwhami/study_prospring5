package com.apress.prospring5.ch9.sec6;

import com.apress.prospring5.ch9.sec6.config.ServicesConfig;
import com.apress.prospring5.ch9.sec6.config.XAJpaConfig;
import com.apress.prospring5.ch9.sec6.entities.Singer;
import com.apress.prospring5.ch9.sec6.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;

public class TxJtaDemo {

    private static Logger logger = LoggerFactory.getLogger(TxJtaDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class,XAJpaConfig.class);
        SingerService singerService = ctx.getBean(SingerService.class);
        Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
        singerService.save(singer);
        if (singer.getId() != null) {
            logger.info("========== --> Saved Singer");
        }  else {
            logger.error("==========  --> Can not saved Singer!!");
        }
    }

}
