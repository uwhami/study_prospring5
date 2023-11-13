package com.apress.prospring5.ch8.sec1.config;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 8.1.1 ㅇㅖ제 코드에서 사용할 예제 데이터 모델.
 * 7장과 비슷하지만 감사 기능 구현을 알아볼때는 해당 기능을 확인하는 용도로 컬럼 몇개와 이력 테이블을 추가함.
 *
 * 8.1.2 JPA의 EntityManagerFactory 구성.
 *
 * 첫번째 구성 방법은, LocalEntityManagerFactoryBean 클래스를 사용하면 퍼시스턴스 유닛 이름만 설정하면 되는 가장 간단한 방법이지만,
 * DataSource를 주입할 수 없어 분산 트랜잭션을 사용할 수 없으므로 간단한 개발 작업에만 적합하다.
 *
 * 두번째 구성 방법은, JEE 호환 컨테이너가 제공하는 엔터티 매니저를 사용하는 것이다.
 * JEE 호환 컨테이너에서 애플리케이션 서버는 배포 서술자 정보를 사용해 JPA 퍼시스턴스 유닛을 부트스트랩한다.
 * JNDI 룩업으로 엔터티 매니저를 룩업하여 사용 할 수 있다.
 *
 * 세번째 구성 방법은, 가장 많이 사용하고 8장에서도 사용하는 방법으로 LocalContainerEntityManagerFactoryBean 클래스를 사용한다.
 * 이 방법은 DataSource 주입과 로컬 및 분산 트랜잭션 참여가 가능하다.
 * 아래는 자바구성 클래스로 해당 내용을 구성했다.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.apress.prospring5.ch8"})
public class DataJpaConfig {

    private static Logger logger = LoggerFactory.getLogger(DataJpaConfig.class);

    @Bean
    public DataSource dataSource(){
        try{
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:/db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql").build();
        }catch(Exception e){
            logger.error("==========Can not create embedded dataSource Bean!", e);
            return null;
        }
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.apress.prospring5.ch8.sec1.entities");
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        hibernateProp.put("hibernate.max_fetch_depth", 3);
        hibernateProp.put("hibernate.jdbc.batch_size", 10);
        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
        return hibernateProp;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

}
