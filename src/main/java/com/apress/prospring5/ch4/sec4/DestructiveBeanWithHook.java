package com.apress.prospring5.ch4.sec4;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

/**
 * 4.4.6 셧다운 후크 사용하기
 *
 * 스프링에서 소멸 콜백의 유일한 단점은 자동으로 호출되지 않는다는 점이다.
 * 애플리케이션 종료 전에 반드시 AbstractApplicationContext.destroy()를 호출해야 한다.
 * 서블릿으로 작동한다면 간단히 서블릿의 destroy() 메서드에서 호출하면 된다.
 * 하지만 단독 실행 가능한 애플리케이션에서는 간단하지 않다.
 *
 * 다행히, 자바는 애플리케이션을 종료하기 직전에 수행되는 스레드인 "셧다운 후크"를 제공한다.
 * 이 메커니즘의 효과를 보는 가장 쉬운 방법은 AbstractApplicationContext의 registerShutdownHook() 메서드를 사용하는 것이다.
 */
public class DestructiveBeanWithHook {
    private File file;
    private String filePath;

    public void setFile(File file) {
        this.file = file;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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
    public void destroy() throws Exception{
        System.out.println("==========Destroying Bean.");

        if(!file.delete()){
            System.out.println("==========Error : Fail to delete file");
        }

        System.out.println("==========Existing File : " + file.exists());
    }


    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec4/app-context-annotation.xml");
        ctx.refresh();

        ctx.getBean(DestructiveBeanWithHook.class);
        ctx.registerShutdownHook();
    }
}
