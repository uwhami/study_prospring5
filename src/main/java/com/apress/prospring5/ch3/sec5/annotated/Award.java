package com.apress.prospring5.ch3.sec5.annotated;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//@Retention : 어노테이션 정보가 유지되는 범위를 지정(
// SOURCE 어노테이션이 소스코드만 존재 컴파일 후 사라짐.
// CLASS 어노테이션이 .class 파일에 존재하지만 런타임에는 사라짐.
// RUNTIME 컴파일러와 런타임에 어노테이션 사용 가능.)
@Target({ElementType.TYPE}) //어노테이션을 부여할 수 있는 대상을 지정.
@Retention(RetentionPolicy.RUNTIME)
public @interface Award {   //@interface 명령어를 사용하여 어노테이션을 직접 정의(ex:Award라는 어노테이션을 직접 정의)

    @AliasFor("prize")
    String[] value() default{}; //책 p172에는 String이 반환타입으로 되어있음...

    @AliasFor("value")
    String[] prize() default{};

}
