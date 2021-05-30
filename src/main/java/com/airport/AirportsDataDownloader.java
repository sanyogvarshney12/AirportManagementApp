package com.airport;

import ch.qos.logback.classic.Logger;
import com.airport.logger.ApplicationLogger;
import org.slf4j.LoggerFactory;

public class AirportsDataDownloader {

    private static Logger LOGGER =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(AirportsDataDownloader.class);
    //Using Java11 features
    public static void main(String[] args) throws Exception {
        LOGGER.info("Welcome 123");
        LOGGER.debug("Welcome 123");
        LOGGER.debug(AirportsDataDownloader.class.getName(),"main", "Welcome");
        IAirportService service = new AirportManagerImpl();
        service.listAllAirports();
    }



}
