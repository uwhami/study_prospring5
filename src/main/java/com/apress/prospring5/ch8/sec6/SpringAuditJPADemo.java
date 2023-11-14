package com.apress.prospring5.ch8.sec6;

import com.apress.prospring5.ch8.sec6.config.DataJpaConfig;
import com.apress.prospring5.ch8.sec6.entities.SingerAudit;
import com.apress.prospring5.ch8.sec6.repos.SingerAuditRepository;
import com.apress.prospring5.ch8.sec6.services.SingerAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringAuditJPADemo {

    private static Logger logger = LoggerFactory.getLogger(SpringAuditJPADemo.class);

    private static void listSingers(List<SingerAudit> singerAudits){
        logger.info("");
        logger.info("========== Singer List");
        singerAudits.forEach(s -> logger.info("========== " + s.toString()));
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        List<SingerAudit> singers = singerAuditService.findAll();
        listSingers(singers);

        logger.info("========== Register New Singer");
        SingerAudit singer = new SingerAudit();
        singer.setFirstName("TEST1");
        singer.setLastName("LALLALLAL");
        singer.setBirthDate(new Date((new GregorianCalendar(1940,8,16)).getTime().getTime()));
        singerAuditService.save(singer);

        singers = singerAuditService.findAll();
        listSingers(singers);

        singer = singerAuditService.findById(1L);
        logger.info("========== selected singer : " + singer.toString());
        singer.setFirstName("CHANGED");
        singerAuditService.save(singer);
        listSingers(singerAuditService.findAll());

        ctx.close();
    }
}
