package com.apress.prospring5.ch6.sec18;

import com.apress.prospring5.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 6.18 스프링 부트 JDBC
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception{

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert(ctx != null);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        String singerName = singerDao.findNameById(1L);
        logger.info("==========Singer : " + singerName);

        System.in.read();
        ctx.close();

    }

}
