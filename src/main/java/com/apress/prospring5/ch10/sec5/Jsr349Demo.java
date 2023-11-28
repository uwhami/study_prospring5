package com.apress.prospring5.ch10.sec5;

import com.apress.prospring5.ch10.sec5.config.AppConfig;
import com.apress.prospring5.ch10.sec5.obj.Genre;
import com.apress.prospring5.ch10.sec5.obj.Singer;
import jakarta.validation.ConstraintViolation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Set;

public class Jsr349Demo {

    private static Logger logger = LoggerFactory.getLogger(Jsr349Demo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        SingerValidationService singerValidationService = ctx.getBean("singerValidationService", SingerValidationService.class);

        Singer singer = new Singer();
        singer.setFirstName("J");
        singer.setLastName("Mayer");
        singer.setGenre(null);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);

        System.out.println("------------------------");

        singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        validateSinger(singer, singerValidationService);

        ctx.close();
    }

    private static void validateSinger(Singer singer, SingerValidationService singerValidationService){
        Set<ConstraintViolation<Singer>> violations = singerValidationService.validateSinger(singer);
        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Singer>> violations){
        for(ConstraintViolation<Singer> violation : violations){
            logger.info("========== Validation Error : "
                    + violation.getPropertyPath()
                    + ", value : " + violation.getInvalidValue()
                    + ", ErrorMessage : " + violation.getMessage());
        }
    }

}
