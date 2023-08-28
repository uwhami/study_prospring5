package com.apress.prospring5.ch3.sec7.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class IngeritanceDemo {

    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-xml.xml");
        ctx.refresh();

        Singer parent = (Singer) ctx.getBean("parent");
        Singer child = (Singer) ctx.getBean("child");

        System.out.println("==========Parent :\n" + parent);
        System.out.println("==========Child \n" + child);

    }

    /**
     * 3.7 빈 상속 결정하기를 마치며...
     * 스프링은 인스턴스에 존재하는 다른 빈의 프로퍼티 설정을 상속받을 수 있도록 <bean>의 정의를 제공한다.
     * 필요에따라서 자식 빈의 특정 프로퍼티 값을 덮어씀으로 완전히 제어할 수 있고,
     * 부모 빈은 각 빈에 기본 구성을 제공할 수 있다.
     *
     * 이러한 빈 상속은 여러 빈 정의를 사용해 애플리케이션을 개발할 때 아주 유용하다.
     * *공유 프로퍼티 값을 사용해 동일한 값의 빈을 여러 개 선언해야 할 떄,
     * *복사와 붙여넣기로 값을 공유하는 방식을 사용하지 말고, 구성에서 상속 계층 구조로 설정해야 한다.
     * 빈 구성을 상속할 때는 자바 상속 계층 구조와 일치시킬 필요가 없다는 사실을 기억해야 한다.
     */

}

