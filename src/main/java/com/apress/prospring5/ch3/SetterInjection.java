package com.apress.prospring5.ch3;

//3-6 수정자 의존성 주입의 예(setter-injection)

/**
 * 컴포넌트의 수정자는 IoC 컨테이너가 관리할수 있도록 의존성을 노출.
 * 의존성 없이도 객체를 생성할 수 있음.
 * 수정자를 호출해서 의존성을 나중에 제공할 수 있음.
 * 컨테이너 내에서 setDependency() 메서드가 필요로 하는 의존성은 자바빈 명명 규칙에 따라 의존을 참조.
 *
 */
public class SetterInjection {
	private Dependency dependency;
	
	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}
	
	@Override
	public String toString() {
		return dependency.toString();
	}
	
}
