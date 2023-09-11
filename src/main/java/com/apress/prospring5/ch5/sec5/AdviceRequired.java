package com.apress.prospring5.ch5.sec5;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 예제 5-38. 포인트컷을 선언하는 애너테이션 클래스.
 *
 * @interface를 타입으로 사용해 인터페이스를 애너테이션으로 선언,
 * @Target 애너테이션은 애너테이션을 타입 레벨과 메서드 레벨로 적용할 수 있게 정의.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AdviceRequired {
}
