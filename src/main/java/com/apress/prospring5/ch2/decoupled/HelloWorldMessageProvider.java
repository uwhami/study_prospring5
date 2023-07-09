package com.apress.prospring5.ch2.decoupled;

import org.springframework.stereotype.Component;

@Component("provider") //간단한 빈 <-- 애노테이션을 사용해 빈 정의를 생성하는 클래스 예 <-- ch3.sec5 에서 쓰임
public class HelloWorldMessageProvider implements MessageProvider{

	@Override
	public String getMessage() {
		return "Hello World!";
	}
	
	
}
