package com.apress.prospring5.ch5.sec4;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 예제 5-9. 사용자 인증 정보를 검사하는 SecurityAdvice 클래스
 */
public class SecurityAdvice implements MethodBeforeAdvice {

    public SecurityManager securityManager;


    /**
     * SecurityManager 인스턴스를 생성자에서 생성하고 해당 인스턴스를 필드에 저장.
     * ThreadLocal을 사용해 모든 데이터를 현재 스레드에 저장하므로
     * 애플리케이션과 SecurityAdvice가 동일한 SecurityManager 인스턴스를 공유하지 않아도 된다.
     */
    public SecurityAdvice(){
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLoggedOnUser();
        if(user == null){
            System.out.println("======No user authenticated");
            throw new SecurityException("==========You must login before attempting to invoke the method: " + method.getName());
        }else if("John".equals(user.getUserName())){
            System.out.println("==========Logged in user is John - OKAY!");
        }else{
            System.out.println("==========Logged user is " + user.getUserName() + " - NOT GOOD :(");
            throw new SecurityException(user.getUserName() + " user is not allowed access to method : " + method.getName());
        }

    }
}
