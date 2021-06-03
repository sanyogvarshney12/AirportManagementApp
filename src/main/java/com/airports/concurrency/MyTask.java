package com.airports.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class MyTask implements Runnable {

    private String name;

    public String getName() {
        return name;
    }

    public MyTask(String taskName) {
        this.name = taskName;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
    }

    public void run() {
        System.out.println("Thread Name : "+Thread.currentThread().getName()+" Task Name : "+name+" Started : "
                + LocalDateTime.now());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread Name : "+Thread.currentThread().getName()+" Task Name : "+name+" Ended : "
                + LocalDateTime.now());
    }
}
