package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/* 3-36. constructor-injection
 * 스프링이 생성자 주입을 할 때 어떤 생성자를 사용해야 할지 판단하지 못할 수 있다.
 * 대개 인수의 개수도 같고 인수의 데이터 type도 같은 두 개의 생성자가 있는 경우에 발생한다.
 */
public class ConstructorConfusion {
	
	private String someValue;
	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) call");
		this.someValue = someValue;
	}
	
	public ConstructorConfusion(int someValue) {
		System.out.println("ConstructorConfusion(int) call");
		this.someValue = "Number Value: " + Integer.toString(someValue);
	}
	
	public String toString() {
		return someValue;
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-xml.xml");
		ctx.refresh();
		ConstructorConfusion cc = (ConstructorConfusion)ctx.getBean("constructorConfusion");
		System.out.println(cc);
		ctx.close();
	}

}
