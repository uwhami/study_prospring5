package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 3-49. SpEL 을 이용해 값을 주입하는 테스트용 빈 클래스.
 */
public class InjectSimpleSpel {
	
	private String name;
	private int age;
	private float height;
	private boolean programmer;
	private Long ageInSeconds;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isProgrammer() {
		return programmer;
	}

	public void setProgrammer(boolean programmer) {
		this.programmer = programmer;
	}

	public Long getAgeInSeconds() {
		return ageInSeconds;
	}

	public void setAgeInSeconds(Long ageInSeconds) {
		this.ageInSeconds = ageInSeconds;
	}
	
	public String toString() {
		return "이름: " + name + "\n"
			 + "나이: " + age + "\n"
			 + "나이(초): " + ageInSeconds + "\n"
			 + "키: " + height  + "\n"
			 + "프로그래머입니까?: " + programmer;
	}



	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-xml.xml");
		ctx.refresh();
		
		InjectSimpleSpel simple = (InjectSimpleSpel) ctx.getBean("injectSimpleSpel");
		System.out.println(simple);
		ctx.close();
	}

}
