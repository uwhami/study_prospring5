package com.apress.prospring5.ch3.sec5.sub5.annotated;

import org.springframework.core.annotation.AliasFor;

/*
 * 3-100. 애너테이션 인수가 고유한 빈 식별자로 사용됨을 명확히 할 수 있음.
 */
@Award
public @interface Trophy {

        @AliasFor(annotation = Award.class, attribute = "value")
        String[] name() default {};
}
