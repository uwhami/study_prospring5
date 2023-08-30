package com.apress.prospring5.ch4.sec4.config;

import com.apress.prospring5.ch4.sec4.DestructiveBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 4.4.4 @Bean 을 사용해 소멸 메서드 정의하기.
 *
 * 소멸 콜백은 사용중인 리소스를 남겨놓거나 불안정한 상태로 두지 않고 안전하게 애플리케이션을 종료할 수 있는 이상적인 매커니즘이다.
 * 하지만 애플리케이션의 요구사항에 따라서 적용 방법을 결정해야 한다.
 * 이식성이 중요하다면 메서드 콜백을 사용하고
 * 구성을 단순화하는 것이 중요하면 DisposableBean 인터페이스나 JSR-250 애너테이션을 사용한다.
 */
public class DestructiveBeanConfig {

    @Configuration
    static class DistructiveBeanConfig{

        @Lazy
        @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
        DestructiveBean destructiveBean(){
            DestructiveBean destructiveBean = new DestructiveBean();

//            System.out.println("==========" + System.getProperty("java.io.tmpdir") + "\n=========="
//                    + System.getProperty("file.separator") + "\n==========" + "test.txt");

            destructiveBean.setFilePath(
                    System.getProperty("java.io.tmpdir")
                            + System.getProperty("file.separator") + "test.txt");

            return destructiveBean;
        }

    }

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(DistructiveBeanConfig.class);
        ctx.getBean(DestructiveBean.class);
        System.out.println("==========Start Calling destroy()");
        ctx.destroy();
        System.out.println("==========End Calling destroy()");
    }

}
