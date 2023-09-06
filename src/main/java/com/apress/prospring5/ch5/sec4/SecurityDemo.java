package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.framework.ProxyFactory;

/**
 * 예제 5-10. SecureBean 클래스에 보안을 적용한 SecurityDemo 예제
 *
 * SecurityAdvice 클래스를 사용해 SecureBean 클래스에 보안을 적용한 예제.
 * 비포 어드바이스는 인수의 수정이 필요한 시나리오 등에도 유용하다.
 */
public class SecurityDemo {

    public static SecureBean getSecureBean(){
        SecureBean target = new SecureBean();

        SecurityAdvice advice = new SecurityAdvice();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);
        SecureBean proxy = (SecureBean) factory.getProxy();

        return proxy;
    }

    public static void main(String[] args) {
        SecurityManager mgr = new SecurityManager();
        SecureBean bean = getSecureBean();

        mgr.login("John", "pwd");
        bean.writeSecureMessage();
        mgr.logout();

        try{
            mgr.login("invalid user", "pwd");
            bean.writeSecureMessage();
        }catch (SecurityException ex){
            System.out.println("==========Error Message: " + ex.getMessage());
        }finally {
            mgr.logout();
        }

        try{
            bean.writeSecureMessage();
        }catch(SecurityException ex){
            System.out.println("==========Error Message: " + ex.getMessage());
        }
    }
}
