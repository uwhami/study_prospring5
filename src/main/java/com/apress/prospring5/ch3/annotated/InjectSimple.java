package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/*
 * 3-46. 애너테이션을 적용해 단순 값을 주입하는 테스트용 빈 클래스
 */
@Service("injectSimple")
public class InjectSimple {

	@Value("John Mayer")
	private String name;
	
	@Value("39")
	private int age;
	
	@Value("1.92")
	private float height;
	
	@Value("false")
	private boolean programmer;
	
	@Value("1241401112")
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-annotation.xml");
		ctx.refresh();
		
		InjectSimple simple = (InjectSimple) ctx.getBean("injectSimple");
		System.out.println(simple);
		
		ctx.close();
	}
	
	public String toString() {
		return "이름: " + name + "\n"
			 + "나이: " + age + "\n"
			 + "나이(초): " + ageInSeconds + "\n"
			 + "키: " + height  + "\n"
			 + "프로그래머입니까?: " + programmer;
	}


}
