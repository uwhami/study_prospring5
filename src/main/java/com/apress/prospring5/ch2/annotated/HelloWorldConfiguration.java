package com.apress.prospring5.ch2.annotated;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospring5.ch2.decoupled.HelloWorldMessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;

//2-15 애너테이션 기반 스프링 구성 클래스(앞 예제의 app-context.xml 파일과 동일
/*
 * 스프링 3.0 버전부터 스프링 기반 애플리케이션을 개발할 때 더이상 xml 구성 파일을 하용하지 않아도
 * 애너테이션과 구성클래스를 사용하면 xml 구성파일을 대체할 수 있음.
 * @ComponentsScan, @Configuration 애너테이션을 붙여 애플리케이션 내의 빈 정의를 스스로 찾음. 
 */
@Configuration
public class HelloWorldConfiguration {

	//<bean id="provider" class="..."/> 구성과 동일함.
	@Bean
	public MessageProvider provider() {
		return new HelloWorldMessageProvider();
	}
	
	//bean id="renderer" class="..."/> 구성과 동등함.
	@Bean
	public MessageRenderer renderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(provider());
		return renderer;		
	}
	
}
