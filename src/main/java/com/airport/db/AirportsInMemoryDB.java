package com.airport.db;


import com.airport.functions.TransformerFunctions;
import com.airport.helper.Config;
import com.airport.helper.PropertyHelper;
import com.airport.model.Airport;
import com.airport.model.Country;
import com.airport.model.NavigationAid;
import com.airport.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sanyog Varshney
 * @version 1.0
 * @since 1.0
 */
public class AirportsInMemoryDB {
    private static final Logger logger = LoggerFactory.getLogger(AirportsInMemoryDB.class);
    private static List<Airport> airportsDB = null;
    private static List<Country> countriesDB = null;
    private static List<Region> regionsDB = null;
    private static List<NavigationAid> naviaidsDB = null;

    private AirportsInMemoryDB() {
    }

    static {
        try {

            airportsDB = Files.readString(Paths.get(PropertyHelper.getProperty(Config.AIRPORTS_CSV.name()))).lines().skip(1).map(TransformerFunctions::stringToAirport).collect(Collectors.toList());
            if (airportsDB != null && logger.isInfoEnabled())
                logger.info(String.format("Airports Database Loaded with %s records", airportsDB.size()));
            regionsDB = Files.readString(Paths.get(PropertyHelper.getProperty(Config.REGIONS_CSV.name()))).lines().skip(1).map(TransformerFunctions::stringToRegion).collect(Collectors.toList());
            if (regionsDB != null && logger.isInfoEnabled())
                logger.info(String.format("Regions Database Loaded with %s records", regionsDB.size()));
            countriesDB = Files.readString(Paths.get(PropertyHelper.getProperty(Config.COUNTRIES_CSV.name()))).lines().skip(1).map(TransformerFunctions::stringToCountry).collect(Collectors.toList());
            if (countriesDB != null && logger.isInfoEnabled())
                logger.info(String.format("Countries Database Loaded with %s records", countriesDB.size()));
            naviaidsDB = Files.readString(Paths.get(PropertyHelper.getProperty(Config.NAVAIDS_CSV.name()))).lines().skip(1).map(TransformerFunctions::stringToNavaid).collect(Collectors.toList());
            if (naviaidsDB != null && logger.isInfoEnabled())
                logger.info(String.format("NavAids Database Loaded with %s records", naviaidsDB.size()));
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * @return
     */
    private static Airport airport = new Airport();

    public static List<Airport> airports() {

        return airportsDB;
    }


    /**
     * @return
     */
    public static List<NavigationAid> navaids() {

        return naviaidsDB;
    }


    /**
     * @return
     */
    public static List<Country> countries() {

        return countriesDB;
    }


    /**
     * @return
     */
    public static List<Region> regions() {

        return regionsDB;
    }
}
