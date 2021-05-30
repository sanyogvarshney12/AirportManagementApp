package com.airport;

import com.airport.logger.ApplicationLogger;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class AirportsDataDownloader {

    private static final ApplicationLogger LOGGER = new ApplicationLogger();
    //Using Java11 features

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        LOGGER.debug(AirportsDataDownloader.class.getName(),"main", "Welcome");
        IAirportService service = new AirportManagerImpl();
        service.listAllAirports();
    }



}
