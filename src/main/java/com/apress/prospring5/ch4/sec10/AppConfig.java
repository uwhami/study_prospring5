package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import com.apress.prospring5.ch3.xml.ConfigurableMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 4.10.1 자바로 ApplicationContext 구성하기
 *
 * @Configuration 애너테이션은 스프링에게 이 클래스는 자바 기반 구성 파일임을 알리는데 사용됨.
 * 이 클래스에는 빈 정의를 나타내는 @Bean 애너테이션이 적용된 메서드가 포함되어 있음.
 * @Bean 애너테이션은 <bean> 태그와 동일하며 메서드 이름은 bean id 애트리뷰트와 동일함.
 */
@Configuration
public class AppConfig {

    @Bean
    public MessageProvider messageProvider(){
        return new ConfigurableMessageProvider();
    }

    @Bean
    public MessageRenderer messageRenderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }

}
