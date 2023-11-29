package com.apress.prospring5.ch11.annotation_scheduled_task.services;

import com.apress.prospring5.ch11.annotation_scheduled_task.ScheduleTaskAnnotationDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service("asyncService")
public class AsyncServiceImpl implements AsyncService{

    private static Logger logger = LoggerFactory.getLogger(ScheduleTaskAnnotationDemo.class);

    @Async
    @Override
    public void asyncTask() {
        logger.info("========== Async Task Start...");

        try{
            Thread.sleep(10000);
        }catch(Exception ex){
            logger.error("========== Task Interrupt ", ex);
        }
        logger.info("========== Complete Async Task.");
    }

    @Async
    @Override
    public Future<String> asyncWithReturn(String name) {
        logger.info("========== Start Async Task - returning name : " + name);
        try{
            Thread.sleep(10000);
        }catch(Exception ex){
            logger.error("========== Task Interrupt ", ex);
        }
        logger.info("========== Complete Async Task.- returning name : " + name);
        //return new AsyncResult<>("Hello: " + name);
        CompletableFuture future = new CompletableFuture<>();
        future.complete("========== Hello : " + name);
        return future;
    }
}
