package com.apress.prospring5.ch6.sec9.config;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.sec8.JdbcSingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 예제 6-32. JdbcTemplate 빈 초기화.
 *
 * JdbcTemplate은 한 번 구성하고 나면 스레드 세이프(Thread-safe) 한다.
 * 즉 스프링 구성에서 JdbcTemplate 인스턴스 하나를 초기화하고 이 인스턴스를 모든 DAO 빈에 주입할 수도 있다.
 * 구성방법이 아래 예제와 같다.
 */
@Configuration
public class EmbeddedJdbcConfig {

    private static Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource(){
        try{
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql").build();
        }catch (Exception e){
            logger.error("==========Embedded DataSource bean cannot be created!",e);
            return null;
        }
    }

    /**
     * 일반적으로 JdbcTemplate 초기화는 setDataSource 내부에서 이루어진다.
     * 스프링이 메서드에 DataSource를 주입하면 JdbcTemplate도 초기화 돼 사용할 수 있다.
     * JdbcSingerDao에서 setDataSource 가 이루어짐.
     */
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public SingerDao singerDao(){
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setJdbcTemplate(jdbcTemplate());
        return dao;
    }

}
