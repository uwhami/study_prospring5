package com.apress.prospring5.ch5.sec4;

/**
 * 예제 5-8. 인증 정보를 저장하는 SecurityManager 클래스.
 *
 * 이 구현내용은 실제 구현이 아닌, 사용자가 인증을 할 수 있다고 가정하는 구현이다.
 */
public class SecurityManager {
    private static ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public void login(String userName, String password){
        threadLocal.set(new UserInfo(userName, password));
    }

    public void logout(){
        threadLocal.set(null);
    }

    public UserInfo getLoggedOnUser(){
        return threadLocal.get();
    }

}
