package com.apress.prospring5.ch6.sec9;

import com.apress.prospring5.ch6.dao.SingerDao;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 6.9.2 JdbcTemplate를 사용해 단일 값 조회하기.
 * 예제 6-34. JdbcSingerDao 테스트 클래스.
 */
public class JdbcCfgTest {

    private void  testDao(SingerDao singerDao){
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertTrue("John Mayer".equals(singerName));
    }

    @Test
    public void testH2DB(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch6/embedded-h2-cfg.xml");
        ctx.refresh();

        testDao(ctx.getBean(SingerDao.class));
        ctx.close();
    }
}
