package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AppConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Singer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.apress.prospring5.ch7.SpringHibernateDemo.listSingersWithAlbum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerDaoTest {

    private static Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testInsert(){
        Singer singer = new Singer();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date((new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My Kind of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1961,7,18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("A Heart Full of Blues");
        album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1962,3,20)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.save(singer);
        assertNotNull(singer.getId());

        List<Singer> singers = singerDao.findAllWithAlbum();
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerDao.findAll();
        listSingersWithAlbum(singers);
    }

    @Test
    public void testFindById(){
        Singer singer = singerDao.findById(1L);
        logger.info("==========" + singer.toString());
    }

    @Test
    public void testUpdate(){
        Singer singer = singerDao.findById(1L);
        assertNotNull(singer);
        assertEquals("Mayer", singer.getLastName());
        Album album = singer.getAlbums().stream().filter(
                a -> a.getTitle().equals("Battle Studies")).findFirst().get();
        singer.setFirstName("John Clayton");
        singer.removeAlbum(album);
        singerDao.save(singer);

        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

    @Test
    public void testDelete(){
        Singer singer = singerDao.findById(2L);
        assertNotNull(singer);
        singerDao.delete(singer);

        listSingersWithAlbum(singerDao.findAllWithAlbum());
    }

}
