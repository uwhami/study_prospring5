package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.DataJpaConfig;
import com.apress.prospring5.ch8.entities.Album;
import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
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

    private static void listSingersWithAlbums(List<Singer> singers){
        logger.info("==========Singer list");
        for(Singer s : singers){
            logger.info("==========" + s.toString());
            s.getAlbums().forEach(album -> logger.info("\t==========" + album.toString()));
        }
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        listSingersWithAlbums(singers);
    }

    @Test
    public void testFindAllWithAlbum(){
        List<Singer> singers = singerService.findAllWithAlbum();
        assertNotNull(singers);
        listSingersWithAlbums(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = singerService.findById(1L);
        assertNotNull(singer);
        logger.info("==========" + singer.toString());
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940,8,16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1961,7,18)).getTime().getTime()));
        singer.addAlbum(album);

        singerService.save(singer);
        assertNotNull(singer);

        List<Singer> singers = singerService.findAllWithAlbum();
        listSingersWithAlbums(singers);
    }

}
