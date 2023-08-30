package com.apress.prospring5.ch4.sec4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

/**
 * 4.4.1 빈이 소멸될 때 메서드를 실행하기.
 * 빈이 소멸될때는 메서드, 인터페이스, 애너테이션으로 메서드를 실행할 수 있으며
 * 스프링은 스로토타입 스코프에 있는 빈의 소멸 콜백 메서드는 호출하지 않는다.
 */
public class DestructiveBean {
    private File file;
    private String filePath;

    public void afterPropertiesSet() throws Exception{
        System.out.println("==========Initialization Bean");

        if(filePath == null){
            throw new IllegalArgumentException("==========" + DestructiveBean.class
             + " : Not Setting filePath Property.");
        }

        this.file = new File(filePath);
        this.file.createNewFile();
        System.out.println("==========Existing File : " + file.exists());
    }

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
        ctx.load("classpath:spring/ch4/sec4/app-context-xml.xml");
        ctx.refresh();

        DestructiveBean bean = (DestructiveBean) ctx.getBean("destructiveBean");

        System.out.println("==========Start Calling destroy()");
        ctx.destroy();  //xml에서 detroy-method 애트리뷰트에 destroy 를 매칭하여 소멸 콜백을 destroy()로 지정. 소멸할때 destroy() 함수를 부르고 소멸함.
        System.out.println("==========End Calling destroy()");

    }


}
