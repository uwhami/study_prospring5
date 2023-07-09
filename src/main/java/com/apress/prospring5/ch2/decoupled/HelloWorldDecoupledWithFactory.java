package com.apress.prospring5.ch2.decoupled;

public class HelloWorldDecoupledWithFactory {

	public static void main(String[] args) {
		MessageSupportFactory mf = MessageSupportFactory.getInstance();
		System.out.println(mf);
		MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
		MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();
		System.out.println(MessageSupportFactory.getInstance());
		mr.setMessageProvider(mp);
		mr.render();
	}

}
