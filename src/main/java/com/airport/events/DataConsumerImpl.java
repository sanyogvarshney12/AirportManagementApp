package com.airport.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DataConsumerImpl implements IDataConsumer {

    private final Logger logger = LoggerFactory.getLogger(DataConsumerImpl.class);

    @Override
    public void readFileAndSplit() throws IOException, InterruptedException {
        //Stream<Path> pathStream = Files.list(Paths.get("C:/Users/sanyo/Desktop/airportData/Event"));
        Supplier<Stream<Path>> supp = () -> {
            try {
                return Files.list(Paths.get("C:/Users/sanyo/Desktop/airportData/Event"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        long count = supp.get().count();
        if(count == 0){
            throw new FileNotFoundException();
        }
        long time = 100; //100ms time constraint to finish all tasks
        long timeToExecuteSingleFile = 5; // 5ms time to process a file
        long file = count; // 10 files to process
        int n = (int)((timeToExecuteSingleFile * count)/time);
        logger.info("Total No of Threads : "+n);
        logger.info("Total No of Files : "+count);
        ExecutorService service = Executors.newFixedThreadPool(n);
        Runnable cons = new Consumer(supp);
        logger.info("Task execution started : "+System.currentTimeMillis());
        service.execute(cons);
        logger.info("Task execution started : "+System.currentTimeMillis());
        if(!service.isTerminated()){
            service.awaitTermination(10000, TimeUnit.MILLISECONDS);
        }else{
            logger.info("Service Terminated");
        }
        service.shutdownNow();
    }

    public static class Consumer implements Runnable {
        private Supplier<Stream<Path>> str;

        public Consumer(Supplier<Stream<Path>> str) {
            this.str = str;
        }

        public void run() {
            str.get().forEach(s -> {
                try {
                    Files.readString(s).lines().sorted();
                    Files.deleteIfExists(s);
                    //Thread.sleep(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        IDataConsumer consumer = new DataConsumerImpl();
        consumer.readFileAndSplit();
    }
}