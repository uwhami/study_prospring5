package com.apress.prospring5.ch15.test;

import com.apress.prospring5.ch15.config.RestClientConfig;
import com.apress.prospring5.ch15.entities.Singer;
import com.apress.prospring5.ch15.entities.Singers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

    final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
    private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/singer/listdata";
    private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/singer/{id}";
    private static final String URL_CREATE_SINGER = "http://localhost:8080/singer/";
    private static final String URL_UPDATE_SINGER = "http://localhost:8080/singer/{id}";
    private static final String URL_DELETE_SINGER = "http://localhost:8080/singer/{id}";

    @Autowired
    RestTemplate restTemplate;

    @Before
    public void setUp() {
        assertNotNull(restTemplate);
    }

    @Test
    public void testFindAll() {
        logger.info("--> 모든 singer 조회 테스트하기");
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        assertTrue(singers.getSingers().size() == 3);
        listSingers(singers);
    }

    @Test
    public void testFindById() {
        logger.info("--> 아이디가 1인 singer 조회 테스트하기");
        Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
        assertNotNull(singer);
        logger.info(singer.toString());
    }

    @Test
    public void testUpdate() {
        logger.info("--> 아이디가 1인 singer 수정 테스트하기");
        Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
        singer.setFirstName("John Clayton");
        restTemplate.put(URL_UPDATE_SINGER, singer, 1);
        logger.info("Singer 수정 성공: " + singer);
    }

    @Test
    public void testDelete() {
        logger.info("--> 아이디가 3인 singer 삭제 테스트하기");
        restTemplate.delete(URL_DELETE_SINGER, 3);
        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        Boolean found = false;
        for(Singer s: singers.getSingers()) {
            if(s.getId() == 3) {
                found = true;
            }
        };
        assertFalse(found);
        listSingers(singers);
    }

    @Test
    public void testCreate() {
        logger.info("--> singer 생성 테스트하기");
        Singer singerNew = new Singer();
        singerNew.setFirstName("BB");
        singerNew.setLastName("King");
        singerNew.setBirthDate(new Date(
                (new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
        singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);

        logger.info("Singer 생성 성공: " + singerNew);

        Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
        listSingers(singers);
    }

    private void listSingers(Singers singers) {
        singers.getSingers().forEach(s -> logger.info(s.toString()));
    }

}
