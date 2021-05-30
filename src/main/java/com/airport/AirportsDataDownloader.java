package com.airport;

import com.airport.logger.ApplicationLogger;

public class AirportsDataDownloader {

    private static final ApplicationLogger LOGGER = new ApplicationLogger();
    //Using Java11 features
    public static void main(String[] args) throws Exception {
        LOGGER.debug(AirportsDataDownloader.class.getName(),"main", "Welcome");
        IAirportService service = new AirportManagerImpl();
        service.listAllAirports();
    }



}
