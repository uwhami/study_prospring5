package com.apress.prospring5.ch3.sec5.sub5.config;

import com.apress.prospring5.ch3.sec5.sub5.annotated.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.Map;

/*
 * 예제 3-95, 3-96 ID값과 별칭 애너테이션 기본 애트리뷰트로 지정
 */
public class AliasConfigDemo {

    @Configuration
    public static class AliasBeanConfig{
        
        // name의 제일 첫번째 값이 id, 그 다음 이름들은 별칭
        @Bean(name={"johnMayer","john","jonathan","johnny"})
        public Singer singer() {
            return new Singer();
        }
    }

    public static void main(String[] args){
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);

        Map<String,Singer> beans = ctx.getBeansOfType(Singer.class);
        beans.entrySet().stream().forEach(b ->
                System.out.println("==========id: " + b.getKey()
                + "\n==========Alias : " + Arrays.toString(ctx.getAliases(b.getKey())) + "\n")
        );
    }

}
