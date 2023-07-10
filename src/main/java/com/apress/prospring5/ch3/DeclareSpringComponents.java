package com.apress.prospring5.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;

//3-21. ApplicationContext로부터 빈을 얻는 방법(setter-injection)
public class DeclareSpringComponents {

	public static void main(String[] args) {
		
		/* 
		 * DecaultListableBeanFactory 대신에 GenericXmlApplicationContext 인스턴스가 생성됨. (ch3.sec4.3-12 XmlConfigWithBeanFactory에서 사용됨)
		 * GenericXmlApplicationContext 클래스는 ApplicationContext 인터페이스의 구현채로
		 * XML 파일에 정의된 구성을 기반으로 스프링으 ApplicationContext를 부트스트랩 할 수 있음.
		 */
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-xml.xml");
		ctx.refresh();
//		MessageRenderer messageRenderer = ctx.getBean("renderer", MessageRenderer.class);
//		messageRenderer.render();
		
		MessageProvider messageProvider = ctx.getBean("provider", MessageProvider.class);
		System.out.println(messageProvider.getMessage());
		ctx.close();

	}

}
