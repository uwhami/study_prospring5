package com.apress.prospring5.ch3.mixed;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//3-25. @ImportResource가 적용된 구성클래스
/*
 * 	구성 클래스는 @ImportResource를 사용해 하나 이상의 XML 파일에서 빈 정의를 가지고 올 수 있다.
 */
@ImportResource(locations = {"classpath:/spring/ch3/app-context-xml.xml"})
@Configuration
public class HelloWorldConfiguration {

}
