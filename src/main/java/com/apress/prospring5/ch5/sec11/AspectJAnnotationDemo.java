package com.apress.prospring5.ch5.sec11;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 예제 5-81. @AspectJ 애너테이션 테스트 예제.
 *
 * AppConfig 클래스를 만들기 전에 실행시키면 에러가 난다.
 * GrammyGuitarist는 Singer 인터페이스를 구현하고 기본적으로 인터페이스 기반 JDK 동적 프록시가 생성된다.
 * 하지만 NewDocumentarist는 엄격하게 Grammy-Guitarist 타입이거나 그것을 상속하는 타입이여야 하는 의존성을 요구한다.
 *
 * 1. Singer 의존성을 받아들이도록 newDocumentarist 를 수정한다.
 * 하지만 이 예제는 Singer 인터페이스에 정의된 메서드를 구현한 클래스가 아닌
 * GrammyGuitarist 클래스의 메서드에 접근하므로 이 방법은 적절하지 않다.
 *
 * 2. 스프링에서 클래스 기반 프록시인 CGLIB를 생성한다.
 * XML 구성을 사용할때는 <aop:aspectj-autoproxy/>태그의 구성을 수정해
 * proxy-target-class 애트리뷰트의 값을 true로 설정한다.
 * 자바 구성 클래스의 경우는 AppConfig 클래스와 같다.
 *
 */
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch5/sec11/app-context-xml.xml");
        ctx.refresh();

        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

    }
}
