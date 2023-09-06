package com.apress.prospring5.ch5.sec4;

/**
 * 예제 5-7. 사용자 정보를 저장하는 클래스.
 */
public class UserInfo {
    private String userName;
    private String password;

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
