package com.apress.prospring5.ch4.sec8;

import org.springframework.context.ApplicationListener;

//Ex 4-44. 이벤트 클래스의 리스너.
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {
        MessageEvent msgEvt = event;
        System.out.println("==========Reception : " + msgEvt.getMessage());
    }


}
