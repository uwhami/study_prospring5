package com.apress.prospring5.ch3.sec6.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Target {

    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target(){
    }

    public Target(Foo foo){
        System.out.println("==========Call Target(Foo)");
    }

    public Target(Foo foo, Bar bar){
        System.out.println("==========Call Target(Foo, Bar)");
    }

    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("==========Setting fooOne Property");
    }

    public void setFooTwo(Foo fooTwo) {
        this.fooTwo = fooTwo;
        System.out.println("==========Setting fooTwo Property");
    }

    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("==========Setting bar Property");
    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-03.xml");
        ctx.refresh();

        Target t = null;

        System.out.println("==========byName Used :\n");
        t = (Target) ctx.getBean("targetByName");

        System.out.println("==========byType Used :\n");
        t = (Target) ctx.getBean("targetByType");

       /*
        * 생성자에 의해 자동와이어링을 이용할때 스프링은 인자 두개짜리 생성자를 사용.
        * 그 이유는 해당 생성자의 두 인자에 모두 빈을 제공할 수 있어서 또다른 생성자를 호출할 필요가 없기 때문.
        */
        System.out.println("==========constructor Used :\n");
        t = (Target) ctx.getBean("targetConstructor");

        ctx.close();

    }


}


