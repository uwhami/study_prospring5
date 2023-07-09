package com.apress.prospring5.ch3.sec5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//3-24. @ComponentScan 이 적용된 구성 클래스(setter-injection) 
@ComponentScan(basePackages = {"com.apress.prospring5.ch3.annotated"})
@Configuration
public class HelloWorldConfiguration {

}
