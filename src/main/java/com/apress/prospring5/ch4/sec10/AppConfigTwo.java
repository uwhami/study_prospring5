package com.apress.prospring5.ch4.sec10;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ConponentScan 애너테이션은 XML 구성파일의 <context:component-scan> 태그와 동일하다.
 */
@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch4.sec10.annotated"})
public class AppConfigTwo {

    @Autowired
    MessageProvider provider;

    @Bean(name="messageRenderer")
    public MessageRenderer messageRenderer(){
        System.out.println("==========Create messageRenderer bean");
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        System.out.println("==========setMessageProvider");
        return renderer;
    }

}
