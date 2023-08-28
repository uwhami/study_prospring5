package com.apress.prospring5.ch4.sec3;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 4.3.1 빈 생성 시 메서드 실행하기
 * 초기화 메서드 init()은 제대로 빈이 구성됐는지 확인할 수 있는 이상적인 방법이다.
 * 이 메커니즘을 사용하면 의존성을 직접 제어할 떄의 제어권한을 하나도 잃지 않으면서 IoC가 제공하는 모든 장점을 활용할 수 있다.
 * 유일한 제약은 인자를 받을 수 없다. 초기화 메서드를 정적으로 선언해도 되지만 인자를 받을 수는 없다.
 *
 * 이 메커니즘의 장점은 정적(static) 초기화 메서드를 사용하려고 할때 사라진다.
 * 정적 초기화 메서드에서는 검증 작업을 위해 빈의 상태 정보에 접근할 수 없기 때문이다.(p.213)
 *
 * 메모리를 절약하려고 빈에서 정적 상태 정보를 사용하고 해당 상태정보를 검증하려고 정적 초기화를 사용한다면
 * 정적 상태 정보를 인스턴트 상에 옮기고 비정적 초기화 메서드를 사용할 것을 검토해야 한다.
 * 스프링에 제공하는 싱글턴 관리 기능을 하용하면 동일한 결과를 얻을 수 있고 테스트가 쉬워진다.
 * 필요에 따라 상태 정보만 다른 빈 인스턴스를 여러 개 생성할 수도 있다.
 *
 * 어떤 인스턴스에서는 여러 빈 인스턴스 사이에 공유되는 정적 상태 정보를 공유해야 하며
 * 이때는 언제든 정적 초기화 메서드를 사용할 수 있다.
 */
public class Singer {

    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void init(){
        System.out.println("==========bean initialization");

        if(name == null){
            System.out.println("==========Using default name");
            name = DEFAULT_NAME;
        }

        if(age == Integer.MIN_VALUE){
            throw new IllegalArgumentException(
                    "\n==========" + Singer.class + " Not Setting age property. "
            );
        }
    }

    @Override
    public String toString() {
        return "==========Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec3/app-context-xml.xml");
        ctx.refresh();

        getBean("singerOne",ctx);
        getBean("singerTwo",ctx);
        getBean("singerThree",ctx);

        ctx.close();
    }

    public static Singer getBean(String beanName, ApplicationContext ctx){
        try{
            Singer bean = (Singer) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        }catch (BeanCreationException ex){
            System.out.println("==========Error occurs during bean configuration " + ex.getMessage() );
            return null;
        }
    }
}
