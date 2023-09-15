package com.apress.prospring5.ch5.sec11.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 예제 5-82. 자바 구성 클래스.
 *
 * 스프링에서 클래스 기반 프록시인 CGLIB를 생성하는 방법.
 * XML 구성을 사용할 때는 <aop:aspectj-autoproxy /> 태그의 구성을 수정해
 * proxy-target-class 애트리뷰트 값을 true로 설정한다.
 *
 * 자바 구성클래스는 이보다 훨씬 간단하다.
 * @EnableAspectJAutoProxy 애너테이션은 <aop:aspectj-autoproxy />와 동일하며
 * proxy-target-class 애트리뷰트와 유사한 proxyTargetClass 라는 애트리뷰트를 가진다.
 * 이 애너테이션은 @Configuration 애너테이션을 적용한 클래스에서 사용하도록 설계됐으며,
 * AspectJ의 @Aspect 애너테이션을 적용한 컴포넌트를 처리할 수 있게 지원한다.
 */
@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch5.sec11"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
