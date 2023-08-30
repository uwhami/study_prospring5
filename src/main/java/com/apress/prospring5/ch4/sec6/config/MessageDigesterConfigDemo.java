package com.apress.prospring5.ch4.sec6.config;

import com.apress.prospring5.ch4.sec6.MessageDigestFactoryBean;
import com.apress.prospring5.ch4.sec6.MessageDigester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 예제 4-28. 자바 구성 클래스로 FactoryBean 구성
 */
public class MessageDigesterConfigDemo {

    @Configuration
    static class MessageDigesterConfig{
        @Bean
        public MessageDigestFactoryBean shaDigest(){
            MessageDigestFactoryBean factoryOne = new MessageDigestFactoryBean();
            factoryOne.setAlgorithmName("SHA1");
            return factoryOne;
        }

        @Bean
        public MessageDigestFactoryBean defaultDigest(){
            return new MessageDigestFactoryBean();
        }

        @Bean
        public MessageDigester digesterAnnotationBean() throws Exception{
            MessageDigester messageDigester = new MessageDigester();
            messageDigester.setDigest1(shaDigest().getObject());
            messageDigester.setDigest2(defaultDigest().getObject());
            return messageDigester;
        }
    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(MessageDigesterConfig.class);

        MessageDigester digester = (MessageDigester) ctx.getBean("digesterAnnotationBean");
        digester.digest("Hello World!");
        ctx.close();
    }
}
