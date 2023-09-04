package com.apress.prospring5.ch4.sec11.config;

import com.apress.prospring5.ch4.sec11.FoodProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 4.11.2 - 예제 4-80 : 테스트 클래스에서 프로파일 활성화.
 *
 * -Dspring.profiles.action JVM 옵션을 대체할 수 있는 구성 애너테이션이 있지만,
 *  테스트 클래스에서만 사용할 수 있습니다.
 *  스프링 애플리케이션 테스트는 13장에서 다루므로 예제 코드만 살펴보겠습니다.
 *
 * 테스트폴더 따로 안만들고 main 폴더에서 실행시켰다.
 * project structure -> module -> main 에서 gradle 추가해주고,
 * build.gradle에서도 implementation 'org.springframework.boot:spring-boot-starter-test' 추가했다.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={KindergartenConfig.class, HighschoolConfig.class})
@ActiveProfiles("kindergarten")
public class ProfileJavaConfigTest {

    @Autowired
    FoodProviderService foodProviderService;

    @Test
    public void testProvider(){
        assertTrue(foodProviderService.provideLunchSet() != null);
        assertFalse(foodProviderService.provideLunchSet().isEmpty());

        assertEquals(2, foodProviderService.provideLunchSet().size());
    }
}
