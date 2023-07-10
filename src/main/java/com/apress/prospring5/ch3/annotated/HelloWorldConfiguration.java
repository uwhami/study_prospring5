package com.apress.prospring5.ch3.annotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//3-24. @ComponentScan 이 적용된 구성 클래스(setter-injection) 
@ComponentScan(basePackages = {"com.apress.prospring5.ch3.annotated"})	//<context:component-scanning ../> 요소와 동일한 역
@Configuration
public class HelloWorldConfiguration {

}
