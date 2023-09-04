package com.apress.prospring5.ch4.sec13;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 4.13 JSR-330 애너테이션을 사용한 구성.
 *
 * XML파일에서 JSR-330을 사용할 때는 특별한 태그가 별도로 필요하지 않다.
 *
 * *** 스프링 기능과 JSR-330 기능의 차이점 ***
 * 1. 스프링이 제공하는 @Autowired 애너테이션을 사용하면 required 애트리뷰트를 사용해 DI가 반드시 제공되어야 하는지 나타낼수있지만
 * JSR-330의 @Inject 애너테이션에는 해당 기능이 없다.(required 대신 스프링이 제공하는 @Required를 사용해도 됨)
 *
 * 2. JSR-330 은 싱글턴과 비싱글턴 빈 스코프만 지원하지만
 * 스프링은 더 많은 범위를 지원하며 해당 기능은 웹 애플리케이션에서 유용하다.
 *
 * 3. 스프링에서는 애플리케이션이 요청하는 시점에 스프링이 빈 인스턴스를 생성하도록 @Lazy 애너테이션을 사용할 수 있지만
 * JSR-330 에는 해당 애너테이션이 없다.
 *
 * 결론은 IoC 컨테이너에 독립적이여야 한다는 요구사항이 있지 않는 이상은
 * JSR-330 애너테이션보다는 더욱 막강한 스프링 기반 애너테이션을 사용하는 것이 좋다!
 *
 */
public class Jsr330Demo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec13/app-context-annotation.xml");
        ctx.refresh();

        MessageRenderer renderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
        ctx.close();

    }
}
