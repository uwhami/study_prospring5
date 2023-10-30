package com.apress.prospring5.ch4.sec4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class DestructiveBeanWithInterface implements InitializingBean, DisposableBean {
    private File file;
    private String filePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==========Initialization Bean");

        if(filePath == null){
            throw new IllegalArgumentException("==========" + DestructiveBean.class
                    + " : Not Setting filePath Property.");
        }

        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("==========Existing File : " + file.exists());
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("==========Destroying Bean.");

        if(!file.delete()){
            System.out.println("==========Error : Fail to delete file");
        }

        System.out.println("==========Existing File : " + file.exists());
    }

    public void setFilePath(String filePath){
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec4/app-context-xml.xml");
        ctx.refresh();

        DestructiveBeanWithInterface bean = (DestructiveBeanWithInterface) ctx.getBean("destructiveBeanWithInterface");

        System.out.println("==========Start Calling destroy()");
//        ctx.destroy();
        System.out.println("==========End Calling destroy()");

    }


}
