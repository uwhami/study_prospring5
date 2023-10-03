package com.apress.prospring5.ch6.sec11.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/jdbc2.properties")
@ComponentScan(basePackages = "com.apress.prospring5.ch6.sec11")
public class AppConfig {

    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${dbusername}")
    private String dbusername;

    @Value("${dbpassword}")
    private String dbpassword;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 커넥션 풀을 사용하는 데이터 소스인 BasicDataSource로 액세스하는 Mysql 데이터베이스를 선언.
     */
    @Bean
    public DataSource dataSource(){
        try{
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(dbusername);
            dataSource.setPassword(dbpassword);
            return dataSource;
        }catch(Exception e){
            logger.error("==========Can not create a DBCP DataSource!", e);
            return null;
        }
    }

}
