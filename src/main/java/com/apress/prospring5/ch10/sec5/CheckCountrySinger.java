package com.apress.prospring5.ch10.sec5;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)   /* 해당 애너테이션이 클래스 레벨에만 적용됨을 의미. */
@Constraint(validatedBy = CountrySingerValidator.class) /* 해당 클래스가 검증기임을 나타냄. */
@Documented
public @interface CheckCountrySinger {
    String message() default "LAST NAME and GENDER are necessary for Country Singer";
    Class<?>[] groups() default {}; /* 적용 가능한 유효성 검증 그룹을 지정. */
    Class<? extends Payload>[] payload() default {}; /* 인터페이스 구현하는 클래스,즉 객체를 지정할 수 있음. */
}
