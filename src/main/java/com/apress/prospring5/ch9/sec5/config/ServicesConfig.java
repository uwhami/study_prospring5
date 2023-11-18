package com.apress.prospring5.ch9.sec5.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 9.5 프로그래밍으로 트랜잭션 사용하기.
 *
 */
@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch9.sec5")
public class ServicesConfig {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public TransactionTemplate transactionTemplate(){
        TransactionTemplate tt = new TransactionTemplate();
        tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_NEVER);
        tt.setTimeout(30);
        tt.setTransactionManager(transactionManager());
//        tt.setTransactionManager(tt.getTransactionManager());
        return tt;
    }




}
