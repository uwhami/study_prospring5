package com.apress.prospring5.ch16.init;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer를 상속한 비어있는 클래스를 제공해
 * 기본적으로 DelegatingFilterProxy를 활성화하도록 스프링에 지시하고 있으므로
 * springSecurityFilterChain은 다른 등록된 javax.servlet.Filter 보다 먼저 사용된다.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {



}
