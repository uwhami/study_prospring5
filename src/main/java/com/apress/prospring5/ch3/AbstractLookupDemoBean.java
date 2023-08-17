package com.apress.prospring5.ch3;

/*
 * 예제 3-72. 메서드 주입을 이용해 Singer 클래스의 인스턴스를 가지고 오는 클래스.
 */
public abstract class AbstractLookupDemoBean implements DemoBean{

    public abstract Singer getMySinger();

    @Override
    public void doSomething(){
        getMySinger().sing();
    }
}
