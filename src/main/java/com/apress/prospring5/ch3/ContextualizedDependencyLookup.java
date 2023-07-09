package com.apress.prospring5.ch3;

//3-4. 컨테이너에서 의존성을 가지고 오는 컴포넌트(CDL-ContextualizedDependencyLookup)
public class ContextualizedDependencyLookup implements ManagedComponent{

	private Dependency dependency;
	
	
	/*
	 * 의존성룩업은 자원을 관리하는 컨테이너에서 의존서을 가지고 온다.
	 * 아래 코드는 의존성 키가 바뀌거나, 컨테이너 인스턴스가 null 이거나, 반환된 의존성이 잘못된 타입일때 여러 부분에서 에러가 발생할 수 있다.
	 * 리팩터링해야할 여지가 많다.
	 * 의존성룩업을 이용하면 애플리케이션의 컴포넌트들을 서로 분리할수 있지만, 다시 결합하는 추가 코드가 필요할수 있기 때문에 복잡해진다.
	 * 결론적으로 의존성 룩업이 아닌 의존성 주입을 더 많이 사용하는 이유가 된다.
	 */
	@Override
	public void performLoookup(Container container) {
		this.dependency = (Dependency) container.getDependency("myDependency");
	}
	
	@Override
	public String toString() {
		return dependency.toString();
	}
	
}
