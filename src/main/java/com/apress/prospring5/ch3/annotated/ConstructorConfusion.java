package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/*
 * 3-39. 생성자에 애너테이션을 적용한 빈 클래스.(constructor-injection)
 * 원하는 생성자 메서드에 Autowired 애너테이션을 적용하면 스프링은 이 메서드를 이용해 인스턴스를 생성하고 지정한 값을 주입한다. 
 *  *값은 외부 구성에서 가지고 오는 것이 바람직하다(com.apress.prospring5.ch3.xml.ConstructorConfusion)
 */
@Service("constructorConfusion")
public class ConstructorConfusion {

	private String someValue;
	
	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) call");
		this.someValue = someValue;
	}
	
	@Autowired
	public ConstructorConfusion(@Value("90")int someValue) {
		System.out.println("ConstructorConfusion(int) call");
		this.someValue = "Number Value : " + Integer.toString(someValue);
	}
	
	public String toString() {
		return someValue;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-annotation.xml");
		ctx.refresh();
		
		ConstructorConfusion cc = (ConstructorConfusion)ctx.getBean("constructorConfusion");
		System.out.println(cc);
		ctx.close();
	}

}
