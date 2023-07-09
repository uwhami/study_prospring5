package com.apress.prospring5.ch2.decoupled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("renderer") //복잡한 서비스 빈 <-- ch3.sec5 에서 쓰임
public class StandardOutMessageRenderer implements MessageRenderer{
	
	private MessageProvider messageProvider;

	@Override
	public void render() {
		if(messageProvider == null) {
			throw new RuntimeException(
			"You must set the property messageProvider of class:"
			+ StandardOutMessageRenderer.class.getName());
		}
		System.out.println(messageProvider.getMessage());
	}
	 

	@Override
	@Autowired //복잡한 서비스 
	public void setMessageProvider(MessageProvider provider) {
		this.messageProvider = provider;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return this.messageProvider;
	}

	
	
	
}
