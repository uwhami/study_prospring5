package com.apress.prospring5.ch3.sec5.annotated;

import com.apress.prospring5.ch3.sec5.annotated.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

public class AnnotatedBeanNaming {
    public static void main(String[] args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch3/app-context-annotated.xml");
        ctx.refresh();

        Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
        System.out.println("===========beans.size() : " + beans.size());
        beans.entrySet().stream().forEach(b -> System.out.println(
                "===============id: " + b.getKey() +
                        "\n ===============Aliases : "  + Arrays.toString(ctx.getAliases(b.getKey())) + "\n"
        ));
        ctx.close();
    }

}
