package com.apress.prospring5.ch10.sec5;


import com.apress.prospring5.ch10.sec3.Singer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("singerValidator")
public class SingerValidator implements Validator {

    /* 전달된 클래스의 타입이 검증기가 검증할 수 있는 타입인지 여부를 나타냄. */
    @Override
    public boolean supports(Class<?> clazz) {
        return Singer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "firstName","firstName.empty");
    }
}
