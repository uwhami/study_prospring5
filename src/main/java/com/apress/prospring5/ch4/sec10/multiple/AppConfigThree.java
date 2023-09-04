package com.apress.prospring5.ch4.sec10.multiple;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 4.10.1 - ex 4-64. 다른 구성클래스를 불러오는 구성 클래스.
 *
 * 구성클래스(AppConfigTree)에서 또다른 구성클래스(AppConfigFour)
 * @Import를 사용해 AppConfigFour 구성 클래스에 정의된 @provider 빈에 접근한다.
 */
@Configuration
@Import(AppConfigFour.class)
public class AppConfigThree {

    @Autowired
    MessageProvider provider;

    @Bean(name = "messageRenderer")
    public MessageRenderer messageRenderer(){
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider);
        return renderer;
    }

}
