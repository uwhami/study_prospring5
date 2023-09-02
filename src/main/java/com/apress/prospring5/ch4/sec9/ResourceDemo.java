package com.apress.prospring5.ch4.sec9;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * 4.9 리소스 접근하기
 *
 * file: 과 http: 프로토콜을 사용할 때 스프링이 UrlResource 인스턴스 또는 UrlResource를 상속한 FileUrlResource를 반환했다.
 */
public class ResourceDemo {

    private static void displayInfo(Resource res) throws Exception{
        System.out.println("==========" + res.getClass());
        System.out.println("==========" + res.getURL().getContent());
        //윈도우에서 file:URI - file://(컴퓨터 이름)/(드라이브 문자):/(파일 경로)
        System.out.println("==========");
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();
        File file = File.createTempFile("test","txt");
        file.deleteOnExit();

        Resource res1 = ctx.getResource("file://" + file.getPath());
        displayInfo(res1);

        Resource res2 = ctx.getResource("classpath:test.txt");
        displayInfo(res2);

        Resource res3 = ctx.getResource("http://www.google.com");
        displayInfo(res3);

    }
}
