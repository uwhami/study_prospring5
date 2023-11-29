package com.apress.prospring5.ch11.annotation_scheduled_task.services;

import java.util.concurrent.Future;

public interface AsyncService {
    void asyncTask();
    Future<String> asyncWithReturn(String name);
}
