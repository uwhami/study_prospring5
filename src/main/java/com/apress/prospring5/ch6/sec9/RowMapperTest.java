package com.apress.prospring5.ch6.sec9;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import com.apress.prospring5.ch6.sec9.config.NamedJdbcCfg;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

public class RowMapperTest {

    @Test
    public void testRowMapper(){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao dao = ctx.getBean(SingerDao.class);
        assertNotNull(dao);
        List<Singer> singers = dao.findAll();
        assertTrue(singers.size() == 3);

        singers.forEach(singer -> {
            System.out.println(singer);
            if(singer.getAlbums() != null){
                for(Album album : singer.getAlbums()){
                    System.out.println("==========" + album);
                }
            }
        });

        System.out.println("==========");

        singers = dao.findByFirstName("John");
        singers.forEach(singer -> {
            System.out.println(singer);
            if(singer.getAlbums() != null){
                for(Album album : singer.getAlbums()){
                    System.out.println("==========" + album);
                }
            }
        });
    }
}
