package com.apress.prospring5.ch4.sec13;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * 4.13 JSR-330 애너테이션을 사용한 구성.
 *
 * 모든 애너테이션이 JSR-220 표준인 java.inject에 속해있다.
 *
 * 빈에 이름을 부여하는 것은 스프링의 <bean> 태그에서 name 애트리뷰트를 사용하는 것과 동일하다.
 * 생성자 주입을 사용하려고 String을 받는 생성자에 @inject 애너테이션을 적용했다.
 *
 *
 * build.gradle 파일에 아래 항목을 추가함.
 * // https://mvnrepository.com/artifact/javax.inject/javax.inject
 * implementation group: 'javax.inject', name: 'javax.inject', version: '1'
 *
 */
@Named("messageProvider")
public class ConfigurableMessageProvider implements MessageProvider {

    private String message = "기본 메시지";

    public ConfigurableMessageProvider(){}

    @Inject
    @Named("message")
    public ConfigurableMessageProvider(String message){
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
