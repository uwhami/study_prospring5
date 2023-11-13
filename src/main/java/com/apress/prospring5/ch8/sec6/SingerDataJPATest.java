package com.apress.prospring5.ch8.sec6;

import com.apress.prospring5.ch8.sec6.entities.Album;
import com.apress.prospring5.ch8.sec6.entities.Singer;
import com.apress.prospring5.ch8.sec6.config.DataJpaConfig;
import com.apress.prospring5.ch8.sec6.services.AlbumService;
import com.apress.prospring5.ch8.sec6.services.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SingerDataJPATest {

    private static Logger logger = LoggerFactory.getLogger(SingerDataJPATest.class);

    private GenericApplicationContext ctx;
    private SingerService singerService;

    private AlbumService albumService;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(DataJpaConfig.class);
        singerService = ctx.getBean(SingerService.class);
        albumService = ctx.getBean(AlbumService.class);
        assertNotNull(singerService);
        assertNotNull(albumService);
    }

    @After
    public void tearDown(){
        ctx.close();
    }

    private static void listSingersWithAlbums(List<Singer> singers){
        logger.info("==========Singer list");
        for(Singer s : singers){
            logger.info("==========" + s.toString());
            s.getAlbums().forEach(album -> logger.info("\t========" + album.toString()));
            s.getInstruments().forEach(inst -> logger.info("\t========" + inst.toString()));
        }
    }

    private static void listSingersWithoutAlbums(List<Singer> singers){
        logger.info("==========Singer list");
        for(Singer s : singers){
            logger.info("==========" + s.toString());
        }
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerService.findAll();
        assertNotNull(singers);
        listSingersWithoutAlbums(singers);
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerService.findByFirstName("John");
        assertTrue(singers.size() > 0);
        listSingersWithoutAlbums(singers);
    }

    @Test
    public void testFindByFirstNameAndLastName(){
        List<Singer> singers = singerService.findByFirstNameAndLastName("John", "Mayer");
        assertTrue(singers.size() > 0);
        listSingersWithoutAlbums(singers);
    }

    @Test
    public void testFindAlbumByTitle(){
        List<Album> albums = albumService.findByTitle("The");
        assertTrue(albums.size() > 0);
        albums.forEach(a -> logger.info("==========" + a.toString()));
    }

}
