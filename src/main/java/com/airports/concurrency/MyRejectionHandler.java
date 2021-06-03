package com.airports.concurrency;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRejectionHandler implements RejectedExecutionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyRejectionHandler.class);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            Lock lock = new ReentrantLock();
            logger.info("Task rejected");
            lock.lock();
            executor.setCorePoolSize(executor.getPoolSize()+1);
            lock.unlock();
            //resubmit the rejected job
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                logger.error("Interrupted Exception", e);
            }
            logger.info("Execution started");
        }
    }
