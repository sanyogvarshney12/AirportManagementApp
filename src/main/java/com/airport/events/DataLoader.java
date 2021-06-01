package com.airport.events;

import com.airport.helper.PropertyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DataLoader {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new Task();
        timer.scheduleAtFixedRate(task, 500, 5000);
    }

}

class Task extends TimerTask {
    private final Logger logger = LoggerFactory.getLogger(Task.class);
    long time = 0;
    @Override
    public void run() {
        time = new Date().getTime();
        Path from = Paths.get(PropertyHelper.getProperty("COUNTRIES_CSV_LOCATION"));
        Path to = null;
        try {
            for(int i=0; i<10; i++) {
                to = Paths.get("C:/Users/sanyo/Desktop/airportData/Event/event"+time+i+".csv");
                Files.copy(from, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Timer fired");
        logger.info("Timer fired");
    }
}
