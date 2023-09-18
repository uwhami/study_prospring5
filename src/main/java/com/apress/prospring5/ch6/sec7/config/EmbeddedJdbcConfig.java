package com.apress.prospring5.ch6.sec7.config;

import com.apress.prospring5.ch6.CleanUp;
import com.apress.prospring5.ch6.sec7.dao.JdbcSingerDao;
import com.apress.prospring5.ch6.sec7.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * 6.6 임베디드 데이터베이스 기능.
 * 예제 6-22. 구성 클래스로 임베디드 데이터베이스 사용 구현.
 *
 * sec7 패키지에 넣은 이유는 6.7 DAO 클래스에서 DataSource 사용하기와 연결되어 있음.
 */
@Configuration
public class EmbeddedJdbcConfig {

    private static Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource(){
        try{
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/h2/schema.sql", "classpath:db/h2/test-data.sql").build();
        } catch (Exception e){
            logger.error("==========Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public SingerDao singerDao(){
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setJdbcTemplate(dataSource());
        return dao;
    }


}
