package com.apress.prospring5.ch5.sec12;

/**
 * 예제 5-87. AspectJ를 사용해 어드바이스를 적용하는 MessageWriter 클래스.
 */
public class MessageWriter {
    public void writeMessage(){
        System.out.println("==========foobar");
    }

    public void foo(){
        System.out.println("==========foo");
    }
}
