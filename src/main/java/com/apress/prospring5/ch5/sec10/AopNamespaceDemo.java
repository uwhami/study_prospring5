package com.apress.prospring5.ch5.sec10;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 예제 5-69. AOP 네임스페이스를 사용.
 *
 * aop 네임스페이스를 사용하면 선언적인 스프링 AOP 구성을 매우 간단하게 할 수 있다.
 * 어드바이스 클래스가 더이상 MethodBeforeAdvice 인터페이스를 구현하지 않는다.
 * 메서드에서 인수를 정의하면 스프링은 처리할 때 자동으로 조인포인트를 메서드로 전달한다.
 *
 * app-context-xml-01,02,03 을 통해 점점 심화하며 예제를 살펴봄.
 *
 * 애프터 어드바이스 타입의 경우,
 * 대상 메서드가 정상적으로 종료되었을때만 적용이 필요하면 애프터리터닝어드바이스를,
 * 정상적으로 종료됐거나 에러로 예외를 던지는지 관계없이 실행이 필요하면 애프터어드바이스를 사용해야 한다.
 *
 */
public class AopNamespaceDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        //ctx.load("classpath:spring/ch5/sec10/app-context-xml-01.xml");
        //ctx.load("classpath:spring/ch5/sec10/app-context-xml-02.xml");
        ctx.load("classpath:spring/ch5/sec10/app-context-xml-03.xml");
        ctx.refresh();

        NewDocumentarist documentarist = (NewDocumentarist) ctx.getBean("documentarist");
        documentarist.execute();
        ctx.close();
    }
}
