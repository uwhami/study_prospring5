package com.apress.prospring5.ch10.sec5;


import com.apress.prospring5.ch10.sec5.obj.Singer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("singerValidationService")
public class SingerValidationService {

    @Autowired
    private Validator validator;

    /* validator.validate 메서드를 호출하면 유효성 검증 결과는 ConstraintViolation<T> 인터페이스의 Set 타입으로 반환됨. */
    public Set<ConstraintViolation<Singer>> validateSinger(Singer singer){
        return validator.validate(singer);
    }

}
