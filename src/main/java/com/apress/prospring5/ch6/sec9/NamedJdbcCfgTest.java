package com.apress.prospring5.ch6.sec9;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.sec9.config.NamedJdbcCfg;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NamedJdbcCfgTest {

    @Test
    public void testCfg(){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        SingerDao singerDao = ctx.getBean(SingerDao.class);
        assertNotNull(singerDao);
        String singerName = singerDao.findNameById(1L);
        assertTrue("John Mayer".equals(singerName));
        ctx.close();
    }
}
