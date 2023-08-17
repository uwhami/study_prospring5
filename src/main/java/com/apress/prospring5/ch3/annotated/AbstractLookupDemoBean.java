package com.apress.prospring5.ch3.annotated;

import com.apress.prospring5.ch3.DemoBean;
import com.apress.prospring5.ch3.Singer;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {

    @Lookup("singer")
    public Singer getMySinger() {
        return null; //동적으로 오버라이딩 됨.
    }

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}
