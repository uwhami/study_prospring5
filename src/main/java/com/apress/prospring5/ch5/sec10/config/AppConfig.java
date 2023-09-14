package com.apress.prospring5.ch5.sec10.config;

import com.apress.prospring5.ch2.common.Contact;
import com.apress.prospring5.ch5.sec8.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Contact guitarist(){
        Contact guitarist = new Contact();
        guitarist.setName("John Mayer");
        return guitarist;
    }

    @Bean
    public Advisor advisor(){
        return new IsModifiedAdvisor();
    }

    @Bean
    ProxyFactoryBean bean(){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(guitarist());
        proxyFactoryBean.addAdvisor(advisor());
        proxyFactoryBean.setProxyTargetClass(true);
        return proxyFactoryBean;
    }

}
