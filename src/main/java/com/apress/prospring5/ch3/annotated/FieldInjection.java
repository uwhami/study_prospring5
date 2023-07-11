package com.apress.prospring5.ch3.annotated;

import org.springframework.context.support.GenericXmlApplicationContext;


/*
 * 3-34. 필드 주입 테스트 실행을 위한 field-injection 클래스.
 * 하지만 필드 주입은 여러 단점이 있어서 일반적으로 사용하지 않는다.
 * - 의존성을 추가하기 쉽지만, 단일 책임 원칙을 위반하지 않도록 주의해야 한다.
 * - 클래스는 public 인터페이스 메서드나 생성자를 이용해 필요한 의존성 타입을 명확하게 전달해야 하는데,
 *   필드주입을 사용하면 어떤 타입의 의존성이 실제로 필요한지, 의존성이 필수인지 여부가 명확하지 않을 수 있다.
 * - 필드주입은 final 필드에 사용할 수 없다. 오로지 생성자 주입만을 이용해 초기화 할수있다.
 * - 의존성을 수동으로 주입해야 하므로 테스트 코드를 작성하기 어렵다.
 */
public class FieldInjection {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context.xml");
		ctx.refresh();
		
		Singer singerBean = ctx.getBean(Singer.class);
		singerBean.sing();
		
		ctx.close();

	}

}
