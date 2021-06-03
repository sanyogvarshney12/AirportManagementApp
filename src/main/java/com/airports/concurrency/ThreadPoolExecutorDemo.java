package com.airports.concurrency;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.*;


public class ThreadPoolExecutorDemo {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ThreadPoolExecutor.class);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        ex.setCorePoolSize(10);
        ex.setKeepAliveTime(10, TimeUnit.SECONDS);
        ex.setMaximumPoolSize(100);
        ex.setRejectedExecutionHandler(new MyRejectionHandler());
        long startTime = System.currentTimeMillis();
        for (int i=0; i<20; i++) {
            MyTask task = new MyTask("Task " + i);
            ex.execute(task);
            if(i == 10) {
                long executionTime = System.currentTimeMillis()-startTime;
                logger.info("Execution Time : "+executionTime);
                if (executionTime > 5000) {
                    ex.shutdown();
                }
            }
        }
    }


}
