package com.apress.prospring5.ch10.sec3;

import com.apress.prospring5.ch10.sec3.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 스프링이 제공하는 타입 변환 서비스 */
public class MultipleConvServDemo {

    private static Logger logger = LoggerFactory.getLogger(MultipleConvServDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Singer john = ctx.getBean("john", Singer.class);
        logger.info("========== Singer : " + john);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        AnotherSinger anotherSinger = conversionService.convert(john, AnotherSinger.class);
        logger.info("========== Another Singer : " + anotherSinger);

        String[] strArray = conversionService.convert("a,b,c", String[].class);
        logger.info("========== String Array : " + strArray[0] + strArray[1] + strArray[2]);

        List<String> listString = new ArrayList<>();
        listString.add("a");
        listString.add("b");
        listString.add("c");
        Set<String> setString = conversionService.convert(listString, HashSet.class);
        setString.forEach(s -> logger.info("========== Set " + s));

    }

}

