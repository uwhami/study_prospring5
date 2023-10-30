package com.apress.prospring5.ch7.config;


import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch7")
@EnableTransactionManagement
public class AppConfig {

    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    /** dataSource Bean : H2 Embedded dataSource 사용 */
    @Bean
    public DataSource dataSource(){
        try{
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                            .addScripts("classpath:db/h2/schema.sql",
                                    "classpath:db/h2/test-data.sql").build();

        }catch(Exception e){
            logger.error("==========Can Not Create Embedded DataSource Bean", e);
            return null;
        }
    }

    private Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

    /**
     * sessionFactory 가 제일 중요!
     * 1. dataSource 빈을 주입.
     * 2. com.apress.prospring5.ch7.entities 패키지 아래 도메인 객체를 스캔하도록 구성.
     * 3. 하이버네이트 세부 구성을 hibernateProperties 프로퍼티로 전달.
     */
    @Bean
    public SessionFactory sessionFactory() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.apress.prospring5.ch7.entities");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.afterPropertiesSet();
        return sessionFactoryBean.getObject();
    }

    /** transactionManager 빈 : 하이버네이트 SessionFactory 는 트랜잭션이 필요한 데이터 액세스에 사용할 트랜잭션 메니저가 필요함. */
    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }

}
