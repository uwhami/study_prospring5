package com.apress.prospring5.ch10.sec2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropEditorDemo {

    public static Logger logger = LoggerFactory.getLogger(PropEditorDemo.class);

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch10/sec2/prop-editor-app-context.xml");
        ctx.refresh();

        Singer eric = ctx.getBean("eric", Singer.class);
        logger.info("========== Eric : " + eric);

        Singer conrtySinger = ctx.getBean("countrySinger", Singer.class);
        logger.info("========== John : " + conrtySinger);

        ctx.close();
    }

}
