package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.DataJpaConfig;
import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.service.SingerSummaryService;
import com.apress.prospring5.ch8.service.SingerSummaryUntypeImpl;
import com.apress.prospring5.ch8.view.SingerSummary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SingerSummaryJPATest {

    private static Logger logger = LoggerFactory.getLogger(SingerSummaryJPATest.class);
    private GenericApplicationContext ctx;
    private SingerSummaryUntypeImpl singerSummaryUntype;
    private SingerSummaryService singerSummaryService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
        singerSummaryService = ctx.getBean(SingerSummaryService.class);
        assertNotNull(singerSummaryUntype);
        assertNotNull(singerSummaryService);
    }

    @After
    public void tearDown(){
        ctx.close();
    }

    @Test
    public void testFindAllUntype(){
        singerSummaryUntype.displayAllSingerSummary();
    }

    @Test
    public void testFindAll(){
        List<SingerSummary> list = singerSummaryService.findAll();
        listSingerSummaries(list);
    }

    private static void listSingerSummaries(List<SingerSummary> singers){
        logger.info("==========Singer list");
        for(SingerSummary s : singers){
            logger.info("==========" + s.toString());
        }
    }

}
