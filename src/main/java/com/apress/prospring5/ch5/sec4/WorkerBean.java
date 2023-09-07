package com.apress.prospring5.ch5.sec4;

/**
 * 예제 5-15. 프로파일링할 WorkerBean 클래스.
 * --> ProfilingDemo 클래스에서 doSomeWork 메서드를 프로파일링 함.
 */
public class WorkerBean {
    public void doSomeWork(int noOfTimes){
        for(int x=0; x<noOfTimes; x++){
            work();
        }
    }

    private void work(){
        System.out.println();
    }
}
