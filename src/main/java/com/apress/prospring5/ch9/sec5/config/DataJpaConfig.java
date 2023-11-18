package com.apress.prospring5.ch9.sec5.config;


import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;

/**
 * ch9. 데이터 접근 빈만 포함되어 있는 클래스
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.apress.prospring5.ch9.sec5.repos"})
@PropertySource("classpath:db/jdbc.properties")
public class DataJpaConfig {

    private static Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean
    public DataSource dataSource(){
        try{
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName("org.h2.Driver");
            dataSource.setDriverClass(driver);
//            dataSource.setUrl(url);
            dataSource.setUrl("jdbc:h2:./data/musicdb");
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        }catch(Exception e){
            logger.error("==========Can not create Populator DataSource Bean!",e);
            return null;
        }
    }



    /*
     * 하이버네이트의 hibernate.hbm2ddl.auto 프로퍼티의 값을 create-drop 으로 설정하면 테이블을 생성하는 SQL 스크립트가 필요하지 않으며
     * 매번 테스트를 실행할 때마다 초기화된 테이블을 사용할 수 있다.
     */
    @Bean
    public Properties hibernateProperties(){
        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
//        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.apress.prospring5.ch9.sec5.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

}
