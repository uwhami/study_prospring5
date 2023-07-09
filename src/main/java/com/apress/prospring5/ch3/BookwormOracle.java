package com.apress.prospring5.ch3;

//3-9 Implementation of Orcale Interface(Setter-Injection) 오라클 클래스의 구현
/*
 * 비지니스 인터페이스인 Oracle 에서 의존성을 정의할 필요가 없다.
 * 주입을 위한 수정자가 사용자 인터페이스의 외부에 존재하도록 노력해야 한다.
 * 특정 비지니스 인터페이스의 모든 구현체들이 어떤 특정한 의존성을 필요로 한다고 완전히 확신할 수 없다면,
 * 구현 클래스 각각이 자신의 의존성을 각자 정의하고
 * 비지니스 인터페이스에는 비지니스 메서드만 유지해야 한다.
 */
public class BookwormOracle implements Oracle{

	private Encyclopedia encyclopedia;
	
	public void setEncyclopedia(Encyclopedia encyclopedia) {
		this.encyclopedia = encyclopedia;
	}
	
	@Override
	public String defineMeaningOfLife() {
		return "Encyclopedias are waste of money - go see the world instead";
	}

	
}
