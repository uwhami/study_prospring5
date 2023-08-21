package com.apress.prospring5.ch3;

/*
 * 예제 3-80. ReplacementTarget 클래스
 * 스프링에서 메서드 대체 기능을 사용해 클래스의 메서드를 대체할 수 있다.
 */
public class ReplacementTarget {
        public String formatMessage(String msg){
            return "<h1>" + msg + "</h1>";
        }
        
        public String formatMessage(Object msg){
            return "<h1>" + msg + "</h1>";
        }
}
