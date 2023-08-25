package com.apress.prospring5.ch3.sec6.xml.complicated;

import com.apress.prospring5.ch3.sec6.xml.Bar;
import com.apress.prospring5.ch3.sec6.xml.Foo;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CTarget {

    private FooImpl1 fooOne1;
    private FooImpl2 fooTwo1;

    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;
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


//    public CTarget(){
//    }
//
//    public CTarget(FooImpl1 foo){
//        System.out.println("==========Call Target(Foo)");
//    }
//
//    public CTarget(FooImpl2 foo, Bar bar){
//        System.out.println("==========Call Target(Foo, Bar)");
//    }
//
//    public void setFooOne(FooImpl1 fooOne) {
//        this.fooOne = fooOne;
//        System.out.println("==========Call Target(fooOne)");
//    }
//
//    public void setFooTwo(FooImpl2 fooTwo) {
//        this.fooTwo = fooTwo;
//        System.out.println("==========Call Target(fooTwo)");
//    }

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-04.xml");
        ctx.refresh();

        System.out.println("\n==========Using byType:\n");
        CTarget t = (CTarget) ctx.getBean("targetByType");
        ctx.close();

    }
}
