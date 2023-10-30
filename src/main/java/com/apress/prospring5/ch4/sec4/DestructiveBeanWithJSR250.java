package com.apress.prospring5.ch4.sec4;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.support.GenericXmlApplicationContext;


import java.io.File;

public class DestructiveBeanWithJSR250 {
    private File file;
    private String filePath;

    @PostConstruct
    public void init() throws Exception{
        System.out.println("==========Initialization Bean");

        if(filePath == null){
            throw new IllegalArgumentException("==========" + DestructiveBean.class
                    + " : Not Setting filePath Property.");
        }

        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("==========Existing File : " + file.exists());
    }

    @PreDestroy
    public void destroy(){
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
        ctx.load("classpath:spring/ch4/sec4/app-context-annotation.xml");
        ctx.refresh();

        DestructiveBeanWithJSR250 bean = (DestructiveBeanWithJSR250) ctx.getBean("destructiveBeanWithJSR250");

        System.out.println("==========Start Calling destroy()");
//        ctx.destroy();  //xml에서 detroy-method 애트리뷰트에 destroy 를 매칭하여 소멸 콜백을 destroy()로 지정. 소멸할때 destroy() 함수를 부르고 소멸함.
        System.out.println("==========End Calling destroy()");

        /*
         * 프로퍼티는 xml에서 주입하고
         * 빈 생성과 소멸은 애너테이션을 사용.
         */

    }

}
