package com.apress.prospring5.ch6.sec5.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

/**
 * 예제 6-15. 자바 구성 클래스를 사용한 DataSource 빈 선언.
 *
 * 원서는 프로퍼티 파일의 jdbc.username 항목을 username 으로 변경했으나
 * 윈도우 환경에서는 로그인한 사용자 ID를 가르키는 username 환경 변수가 우선하므로
 * 프로퍼티 파일에 지정한 username 을 사용하려면 추가 설정이 필요하다.
 * 설정 작업을 간단히 하고자 db를 앞에 붙였다.
 */
@Configuration
@PropertySource("classpath:db/jdbc2.properties")
public class DbConfig {

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${dbusername}")
    private String username;

    @Value("${dbpassword")
    private String password;

    @Bean
    static PropertySourcesPlaceholderConfigurer
        propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @SuppressWarnings("unchecked")
    @Lazy
    @Bean
    public DataSource dataSource(){
        try{
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }catch (Exception e){
            return null;
        }
    }

}
