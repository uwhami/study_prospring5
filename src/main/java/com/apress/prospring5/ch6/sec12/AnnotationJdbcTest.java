package com.apress.prospring5.ch6.sec12;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import com.apress.prospring5.ch6.sec11.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;


import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AnnotationJdbcTest {

    private GenericApplicationContext ctx;
    private SingerDao singerDao;

    public void listSingers(List<Singer> singers){
        for(Singer entity : singers){
            System.out.println("==========" + entity.toString());
            if(entity.getAlbums() != null){
                entity.getAlbums().forEach(album -> {
                    System.out.println("==========" + album.toString());
                });
            }
        }
    }

    @Before
    public void setUp(){
        ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
    }

    @Test
    public void testFindAll(){
        List<Singer> singers = singerDao.findAll();
        assertTrue(singers.size() > 0);
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testFindByFirstName(){
        List<Singer> singers = singerDao.findByFirstName("John");
        assertTrue(singers.size() > 0);
        listSingers(singers);
        ctx.close();
    }

    @Test
    public void testSingerUpdate(){

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
        System.out.println("========== --------------------");

        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("Mayer2");
        singer.setBirthDate(new Date((new GregorianCalendar(1977,8,15)).getTime().getTime()));
        singerDao.update(singer);

        singers = singerDao.findAll();
        listSingers(singers);
    }

    /** 6.13 (sec13) 데이터 등록 및 생성된 키 조회하기. */
    @Test
    public void testSingerInsert(){
        Singer singer = new Singer();
        singer.setFirstName("Dajeong");
        singer.setLastName("Kim");
        singer.setBirthDate(new Date((new GregorianCalendar(1991,12,22)).getTime().getTime()));
        singerDao.insert(singer);

        List<Singer> singers = singerDao.findAll();
        listSingers(singers);
    }

    /** 6.14 (sec14) BatchSqlUpdate를 사용하는 배치 조작 */
    @Test
    public void testSingerInsertWithAlbum(){
        Singer singer = new Singer();
        singer.setFirstName("JH");
        singer.setLastName("Shin");
        singer.setBirthDate(new Date((new GregorianCalendar(1991,12,14)).getTime().getTime()));

        Album album = new Album();
        album.setTitle("My kind of Blues");
        album.setReleaseDate(new Date((new GregorianCalendar(1961,7,18)).getTime().getTime()));
        singer.addAlbum(album);

        album = new Album();
        album.setTitle("Second Album");
        album.setReleaseDate(new Date((new GregorianCalendar(1962,8,19)).getTime().getTime()));
        singer.addAlbum(album);

        singerDao.insertWithAlbum(singer);

        List<Singer> singers = singerDao.findAllWithAlbums();
        listSingers(singers);
    }

    @After
    public void tearDown(){
        ctx.close();
    }

}
