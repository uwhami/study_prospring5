package com.apress.prospring5.ch7.config;

import com.apress.prospring5.ch6.CleanUp;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;

/**
 * 7.8 엔터티로 테이블을 생성하도록 하이버네이트 구성하기.
 * hibernate.hbm2ddl.auto 라는 하이버네이트 프로퍼티를 지정하면 엔터티를 사용해 데이터베이스 테이블을 새엉할 수 있다.
 * 처음으로 실행할 때는 create 로 값을 설정하면 엔터티를 스캔해 테이블을 만든다.
 */
@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch7")
@EnableTransactionManagement
@PropertySource("classpath:db/jdbc.properties")
public class AdvancedConfig {

    private static Logger logger = LoggerFactory.getLogger(AdvancedConfig.class);

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * hibernate.hbm2ddl.auto 프로포티를 create-drop 으로 설정하면 시작 전 데이터베이스를 생성하고 테스트를 수행한 뒤 생성된 데이터베이스를 폐기할 수 있다.
     * 데이터소스로는 dbcp 폴드(Pooled) 데이터 소스를 사용한다.
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        try{
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return (DataSource) dataSource;
        } catch (Exception e){
            logger.error("==========Can not create DBCP DataSource Bean!", e);
            return null;
        }
    }

    public Properties hibernateProperties(){
        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.apress.prospring5.ch7.entities")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp() {
        return new CleanUp(new JdbcTemplate(dataSource()));
    }
}
