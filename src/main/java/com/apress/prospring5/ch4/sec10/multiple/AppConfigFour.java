package com.apress.prospring5.ch4.sec10.multiple;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ex 4-63. 또 다른 구성클래스인 AppConfigFour.
 * AppConfigThree에서 불러서 사용한다.
 */
@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch4.sec10.annotated"})
public class AppConfigFour {
}
