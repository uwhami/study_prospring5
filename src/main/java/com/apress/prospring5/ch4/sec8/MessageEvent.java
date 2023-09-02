package com.apress.prospring5.ch4.sec8;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

//Ex.4-43 간단한 이벤트 클래스.
public class MessageEvent extends ApplicationEvent {
    private String msg;

    public MessageEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }
}
