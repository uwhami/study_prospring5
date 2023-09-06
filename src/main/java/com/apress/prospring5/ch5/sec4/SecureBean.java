package com.apress.prospring5.ch5.sec4;

/**
 * 예제 5-6. AOP를 사용해 보호할 SecureBean 클래스
 *
 * 이 예제는 비포 어드바이스를 사용법을 알아보는 예제에 불과하며,
 * 스프링 빈 메서드 실행에 보안을 적용하는 용도라면 스프링 시큐리티(String Security) 프로젝트가 이미 폭넓은 지원 기능을 제공하므로
 * 이런 기능을 직접 구현하지 않아도 된다.
 *
 */
public class SecureBean {
    public void writeSecureMessage(){
        System.out.println("==========Every time I learn something new, "
                             + "it pushes some old stuff out of my bran.");
    }
}
