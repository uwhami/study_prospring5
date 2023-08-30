package com.apress.prospring5.ch4.sec6.config;

import com.apress.prospring5.ch4.sec6.MessageDigestFactoryBean;
import com.apress.prospring5.ch4.sec6.MessageDigester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 예제 4-28. 자바 구성 클래스로 FactoryBean 구성
 *
 *  * FactoryBean은 new 연산자로 인스턴스를 생성할 수 없는 클래스를 다룰때 완벽한 솔루션이다.
 *  * 자바 클래스로 구성을 할 때는 FactoryBean 사용방법에 차이가 있다.
 *  * 컴파일러가 적절한 타입으로 프로퍼티를 설정하도록 제한한다.
 *  * 따라서 getObject() 메서드를 명시적으로 호출해야 한다.
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
