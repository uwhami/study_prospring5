package com.apress.prospring5.ch3;

import com.apress.prospring5.ch3.DemoBean;

/*
 * 예제 3-71. 수정자 주입을 이용해 Singer 클래스의 인스턴스를 가지고 오는 클래스.
 */
public class StandardLookupDemoBean implements DemoBean {

    private Singer mySinger;

    public void setMySinger(Singer mySinger) {
        this.mySinger = mySinger;
    }

    @Override
    public Singer getMySinger() {
        return this.mySinger;
    }

    @Override
    public void doSomething() {
        mySinger.sing();
    }
}
