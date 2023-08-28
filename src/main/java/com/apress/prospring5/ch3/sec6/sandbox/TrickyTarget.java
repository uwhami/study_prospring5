package com.apress.prospring5.ch3.sec6.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("gigi")
@Lazy   //Lazy 애너테이션은 처음 접근이 일어날 때 인스턴스가 생성될 빈을 정의하려고 클래스에 적용되는 애너테이션(XML의 lazy-init과 동일)
public class TrickyTarget {

    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    public TrickyTarget(){
        System.out.println("==========TrickyTarget.constructor()");
    }

    public TrickyTarget(Foo fooOne){
        System.out.println("=========TrickyTarget(Foo) Call");
    }

    public TrickyTarget(Foo fooOne, Bar bar){
        System.out.println("TrickyTarget(Foo, Bar) Call");
    }

    @Autowired
    @Qualifier("fooImplOne")
    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;
        System.out.println("==========Setting fooOne Property");
    }

    @Autowired
    @Qualifier("fooImplTwo")
    public void setFooTwo(Foo fooTwo) {
        this.fooTwo = fooTwo;
        System.out.println("==========Setting fooTwo Property");
    }

    @Autowired
    public void setBar(Bar bar) {
        this.bar = bar;
        System.out.println("==========Setting bar Property");
    }

//    FooImplOne foo1;
//    FooImplTwo foo2;
//
//    @Autowired
//    public void setFoo1(FooImplOne foo1) {
//        this.foo1 = foo1;
//        System.out.println("==========Setting FooImplOne Property");
//    }
//
//    @Autowired
//    public void setFoo2(FooImplTwo foo2) {
//        this.foo2 = foo2;
//        System.out.println("==========Setting FooImplTwo Property");
//    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-05.xml");    //책에는 app-context-04로 되어있으나 정정(p.192)
        ctx.refresh();

        TrickyTarget t = ctx.getBean(TrickyTarget.class);   //XML에서 Autowired를 byType 으로 설정한 것이 아니라 어노테이션을 통해서 타입을 불러옴.
        ctx.close();

    }

}
