package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * 3-44. 단순 값 주입 테스트를 위한 빈 클레스(Simple type)
 * 빈의 프로퍼티를 정의 한 다음에 p의 네임스페이스를 사용해서 프로퍼티에 값을 주입함.
 */
public class InjectSimple {

	private String name;
	private int age;
	private float height;
	private boolean programmer;
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/ch3/app-context-simple-xml.xml");
		ctx.refresh();
		
		InjectSimple simple = (InjectSimple) ctx.getBean("injectSimple");
		System.out.println(simple);
		ctx.close();
	}

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

}
