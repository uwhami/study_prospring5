package com.apress.prospring5.ch3.sec5.sub7.annotated;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/*
 * DepondsOn 애너테이션을 적용하여 의존성을 스프링에 알려줌.
 */
@Component("johnMayer")
@DependsOn("gopher")
public class Singer implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Guitar guitar;

    public Singer(){
    }

    public void sing(){
        guitar = applicationContext.getBean("gopher", Guitar.class);
        guitar.sing();
    }
}
