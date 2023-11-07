package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.DataJpaConfig;
import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SingerJPATest {

    private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        assertNotNull(singerService);
    }

    @After
    public void tearDown(){
        ctx.close();
    }

    private static void listSingers(List<Singer> singers){
        logger.info("==========Singer list");
        for(Singer s : singers){
            logger.info("==========" + s.toString());
            s.getAlbums().forEach(album -> logger.info("==========" + album.toString()));
        }
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        assertNotNull(singers);
        listSingers(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        logger.info("==========" + singer.toString());
    }

}
