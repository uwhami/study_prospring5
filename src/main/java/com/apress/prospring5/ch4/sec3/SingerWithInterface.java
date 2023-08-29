package com.apress.prospring5.ch4.sec3;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SingerWithInterface implements InitializingBean {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = -1;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception { //4.3.1 단원에서 했던 init() 메서드와 같은 역할.
        System.out.println("==========Initialization Bean");

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

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec3/app-context-xml.xml");
        ctx.refresh();

        getBean("singleWithImplOne",ctx);
        getBean("singleWithImplTwo",ctx);
        getBean("singleWithImplThree",ctx);

        ctx.close();
    }

    public static SingerWithInterface getBean(String beanName, ApplicationContext ctx){
        try{
            SingerWithInterface bean = (SingerWithInterface) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch(BeanCreationException ex){
            System.out.println("==========Error occurs during bean configuration" + ex.getMessage());
            return null;
        }
    }


}
