package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;

@Service("renderer")
public class StandardOutMessageRenderer implements MessageRenderer{

	MessageProvider messageProvider;
	
	@Override
	public void render() {
	}

	/*
	 * Autowired 대신에 Resource(name="messageProvider")를 사용해도 같은 결과를 얻을 수 있음.
	 * Resource 애너테이션은 Autowired와 달리 좀더 세부적인 DI 요구에 대응하려는 목적으로 name 인자를 제공함.
	 */
	@Override
	@Autowired
	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return null;
	}
	
}
