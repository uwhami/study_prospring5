package com.apress.prospring5.ch10.sec5;

import com.apress.prospring5.ch10.sec5.obj.Singer;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public void initialize(CheckCountrySinger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Singer singer, ConstraintValidatorContext context) {
        boolean result = true;
        if(singer.getGenre() != null
                && (singer.isCountrySinger() && (singer.getLastName() == null || singer.getGender() == null) )){
            result = false;
        }
        return result;
    }
}
