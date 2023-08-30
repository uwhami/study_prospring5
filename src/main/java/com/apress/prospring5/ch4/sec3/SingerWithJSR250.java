package com.apress.prospring5.ch4.sec3;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 4.3.3 애너테이션을 사용한 빈 초기화 호출.
 * 애너테이션을 사용할때는 사용하는 IoC 컨테이너가 JSR-250을 확실히 지원하는지 확인해야 함.
 *
 * 이식성이 중요하다면 초기화 메서드나 애너테이션을 사용.
 * 그렇지 않다면 InitializingBean 이넡페이스를 하용해 애플리케이션이 해야 할 구성을 줄이는 것이 나음.
 */
public class SingerWithJSR250 {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = -1;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @PostConstruct
    private void init() throws Exception{
        System.out.println("==========bean initialization");

        if(name == null){
            System.out.println("==========Using default name");
            name = DEFAULT_NAME;
        }

        if(age == -1){
            throw new IllegalArgumentException(
                    "\n==========" + Singer.class + " Not Setting age property. "
            );
        }
    }

    @Override
    public String toString() {
        return "==========SingerWithInterface{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec3/app-context-xml.xml");
        ctx.refresh();

        getBean("singerWithJRS250One",ctx);
        getBean("singerWithJRS250Two",ctx);
        getBean("singerWithJRS250Three",ctx);

        ctx.close();
    }

    public static SingerWithJSR250 getBean(String beanName, ApplicationContext ctx){
        try{
            SingerWithJSR250 bean = (SingerWithJSR250) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }catch (BeanCreationException ex){
            System.out.println("==========Error occurs during bean configuration " + ex.getMessage() );
            return null;
        }
    }




}
