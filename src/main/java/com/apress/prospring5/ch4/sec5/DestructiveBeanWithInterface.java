package com.apress.prospring5.ch4.sec5;

import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;

/**
 * 4.5.2 ApplicationContextAware 인터페이스 사용하기.
 *
 * ShutDownHook 빈에 ApplicationContext 참조를 전달해 셧다운 후쿠를 등록하여
 * 메인 어플리케이션에 destroy() 메서드를 호출하지 않았지만
 * shutdownHook 빈이 셧다운 후크를 등록하므로 애플리케이션 종료 전에 등록된 셧다운 후크가 destroy()를 호출하는것을 확인할 수 있다.
 *
 * !!app-context-annotation.xml 파일을 그대로 복붙해와서 필요한것만 수정하다가
 * default-lazy-init="true" 이부분도 같이 가지고 와버렸는데,
 * 그러면 최초에 빈을 생성하는 것이 아니라 빈을 호출할때만 생성되니까 Destroying 과정이 바로 뜨지 않았다.
 */
public class DestructiveBeanWithInterface {
    private File file;
    private String filePath;

    @PostConstruct
    public void afterPropertiesSet() throws Exception{
        System.out.println("==========Initialization Bean");

        if(filePath == null){
            throw new IllegalArgumentException("==========You must specify the filePath property of" + DestructiveBeanWithInterface.class);
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

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "==========DestructiveBeanWithInterface{" +
                "file=" + file +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/ch4/sec5/app-context-annotation.xml");
        ctx.refresh();

        ctx.getBean("destructiveBean", DestructiveBeanWithInterface.class);

    }
}
