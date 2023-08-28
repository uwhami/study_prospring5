package com.apress.prospring5.ch3.sec6.xml.complicated;

import com.apress.prospring5.ch3.sec6.xml.Bar;
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

        /*
         * Foo 인터페이스를 상속받은 클래스가 2개가 있어서,
         * 빈을 발견했지만 어떤 빈을 어디에 써야 할지 모르니 발생하는 오류이다.
         *
         * 이 문제를 해결하는 방법
         * 1. 빈 정의에서 primary 애트리뷰트 값을 true로 설정해 스프링이 자동와이어링을 할때 먼저 사용하게 하는 것.
         * ---> 하지만 primary 는 오직 두 개의 빈 타입만 존재하는 경우에 사용할 수 있는 해결책이다.
         *
         * 2. 빈 이름을 지정하고 XML로 주입할 위치를 구성하는 방법으로 빈 주입 위치에 대한 완전한 제어권을 제공한다.
         */
        System.out.println("\n==========Using byType:\n");
        CTarget t = (CTarget) ctx.getBean("targetByType");  //XML에서 설정한 bean id, Type으로 Autowired 되도록 설정해두었음.
        ctx.close();

    }
}
