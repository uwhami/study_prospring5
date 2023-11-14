package com.apress.prospring5.ch8.sec8;

import com.apress.prospring5.ch8.sec8.config.AuditConfig;
import com.apress.prospring5.ch8.sec8.config.EnversConfig;
import com.apress.prospring5.ch8.sec8.entities.SingerAudit;
import com.apress.prospring5.ch8.sec8.services.SingerAuditService;
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
//        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AuditConfig.class);
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EnversConfig.class);
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

        singer = singerAuditService.findById(4L);
        logger.info("========== selected singer : " + singer.toString());
        singer.setFirstName("CHANGED");
        singerAuditService.save(singer);
        listSingers(singerAuditService.findAll());

        /*8.9 Envers를 통해서 이력관리를 조회한다.*/
        SingerAudit oldSinger = singerAuditService.findAuditByRevision(4L,1);
        logger.info("");
        logger.info("========== singerId:1, version:0 " + oldSinger.toString());
        logger.info("");
        oldSinger = singerAuditService.findAuditByRevision(4L,2);
        logger.info("");
        logger.info("========== singerId:1, version:1 " + oldSinger.toString());
        logger.info("");

        ctx.close();
    }
}
