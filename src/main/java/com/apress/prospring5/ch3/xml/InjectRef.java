package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring5.ch3.Oracle;

/*
 * 3-52. 빈을 주입할 수 있도록 수정자를 노출한 빈 클래스.
 * 주입되는 빈의 타입이 대상 빈에 정의된 타입과 정확히 같을 필요가 없다. 타입은 서로 호환되면 된다.
 * 대상 빈에 선언된 타입이 인터페이스라면 주입된 타입이 이 인터페이스의 구현체 이거나,
 * 선언된 타입이 클래스라면 주입된 타입은 동일한 타입이거나 해당 타입을 상속한 타입이여야 한다.
 */
public class InjectRef {

	private Oracle oracle;
	
	public void setOracle(Oracle oracle) {
		this.oracle = oracle;
	}
	
	@Override
	public String toString() {
		return oracle.defineMeaningOfLife();
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-xml.xml");
		ctx.refresh();
		
		InjectRef injectRef = (InjectRef) ctx.getBean("injectRef");
		System.out.println(injectRef);
		ctx.close();
		
	}

}
