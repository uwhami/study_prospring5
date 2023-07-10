package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.apress.prospring5.ch2.decoupled.MessageProvider;


@Service("provider")
public class ConfigurableMessageProvider implements MessageProvider{
	
	private String message;
	
	
	/*
	 * 3-32.Value를 통해 생성자에 주입할 값을 정의함.
	 */
//	@Autowired
//	public ConfigurableMessageProvider(@Value("Configurable message") String message) {
//		this.message = message;
//	}
	
	
	/*
	 * 3-34. Value 애너테이션을 제거.
	 * message 빈과 이 빈의 ID가 ConfigurableMessageProvider 클래스의 생성자에 지정된 인자의 이름과 동일하게 선언되었기 떄문에
	 * Autowired 애너테이션을 감지하여 생성자 메서드에 주입할 것.
	 */
	@Autowired
	public ConfigurableMessageProvider(String message) {
		this.message = message;
	}
	

	@Override
	public String getMessage() {
		return this.message;
	}

	
	
}
