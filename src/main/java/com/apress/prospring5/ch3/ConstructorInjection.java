package com.apress.prospring5.ch3;

//3-5. 생성자 의존성 주입의 예(constructor-injection)
public class ConstructorInjection {

	private Dependency dependency;
	
	public ConstructorInjection(Dependency dependency) {
		this.dependency = dependency;
	}
	
	@Override
	public String toString() {
		return dependency.toString();
	}
	
}
