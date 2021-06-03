package com.airports.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class CustomThreadPool {
    public static void main(String[] args) throws InterruptedException {
        final BlockingQueueDemo demo = new BlockingQueueDemo();
        IntStream.range(0, 1022).forEach(r -> {
            try {
                demo.enqueue("xx");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    static class BlockingQueueDemo {
        List<Object> queue = new LinkedList<>();

        public synchronized void enqueue(Object item) throws InterruptedException {
            if (queue.size() == 10) {
                wait();
            }
            this.queue.add(item);
            if (this.queue.size() == 1) {
                notifyAll();
            }
        }

        public synchronized void dequeue(Object item) throws InterruptedException {
            while (this.queue.size() == 0) {
                wait();
            }
            if (this.queue.size() == 10) {
                notifyAll();
            }
            this.queue.remove(0);
        }
    }
}