package com.airport;

import com.airport.constants.AirportType;
import com.airport.exception.NoAirportsFoundForContinentException;
import com.airport.exception.NoHeliportFoundException;
import com.airport.logger.ApplicationLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.stream.Collectors;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class AirportManagerImpl implements IAirportService{

    private static final String CLASSNAME = AirportManagerImpl.class.getName();
    private static final Logger log = LoggerFactory.getLogger(AirportManagerImpl.class);
    private ApplicationLogger logger = new ApplicationLogger();
    private static final String METHODSTARTMSG = "***** Method Started *****";
    private static final String METHODENDMSG = "***** Method Ended *****";


    public int listAllAirports(List<String> airports){
        String methodName = "listAllAirports()";
        log.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> smallAirports = airports.stream().filter(airport->smallAirport(airport))
                .collect(Collectors.toList());
        List<String> largeAirports = airports.stream().filter(airport->largeAirport(airport))
                .collect(Collectors.toList());
        List<String> mediumAirports = airports.stream().filter(airport->mediumAirport(airport))
                .collect(Collectors.toList());
        List<String> heliport = airports.stream().filter(airport->heliport(airport))
                .collect(Collectors.toList());
        List<String> closed = airports.stream().filter(airport->closed(airport))
                .collect(Collectors.toList());
        List<String> baloonAirports = airports.stream().filter(airport->baloonAirport(airport))
                .collect(Collectors.toList());
        List<String> seaplaneBase = airports.stream().filter(airport->seaplaneBase(airport))
                .collect(Collectors.toList());

        int totalAirports = smallAirports.size()+largeAirports.size()+mediumAirports.size()
                +closed.size()+baloonAirports.size()+heliport.size()+seaplaneBase.size();
        logger.debug(CLASSNAME, methodName, "For people who fly: {} and counting...", totalAirports);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return totalAirports;
    }

    public String findAirportByName(String name, List<String> airports) {
        String methodName = "findAirportByName()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByName = airports.stream().filter(airport->airport.contains(name))
                .collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Airport Details : {}", airportByName.get(0));
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByName.get(0);
    }

    /**
     *
     * @return
     */
    public List<String> findAirportByCountry(String country, List<String> airports) {
        String methodName = "findAirportByCountry()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByCountry = airports.stream().filter(airport->airport.contains(country))
                .collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Airport Details : {}", airportByCountry);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByCountry;
    }

    public List<String> findAirportByType(String type, List<String> airports) {
        String methodName = "findAirportByType()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByType = airports.stream().filter(airport->airport.contains(type))
                .collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Airport Details : {}", airportByType);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByType;
    }

    public long findHelipads(List<String> airports) {
        String methodName = "findHelipads()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> helipadsList = airports.stream().filter(AirportManagerImpl::heliport)
                .collect(Collectors.toList());
        long size = helipadsList.stream().findAny()
                .orElseThrow(()-> new NoHeliportFoundException("No Heliports found in the system.")).lines().count();
        logger.debug(CLASSNAME, methodName, "Number of Heliports found : {}", size);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return size;
    }

    public List<String> findAirportsByContinent(String continent, List<String> airports) {
        String methodName = "findAirportsByContinent()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByContinent = airports.stream().filter(airport->airport.contains(continent))
                .collect(Collectors.toList());
        airportByContinent.stream().findAny()
                .orElseThrow(()->new NoAirportsFoundForContinentException("No Airport found for this Continent"))
                .lines().count();
        logger.debug(CLASSNAME, methodName, "Airport Details : {}", airportByContinent);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByContinent;
    }

    @Override
    public List<String> listContinents() throws IOException {
        String methodName = "listContinents()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> countriesList = Files.readString(Paths
                .get("C:\\Users\\sanyo\\Desktop\\airportData\\countries.csv"))
                .lines().collect(Collectors.toList());
        countriesList.remove(0);
        List<String> continentList = countriesList.stream().map(s-> splitContinents(s)).distinct().sorted().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Countries : {}", continentList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return continentList;
    }

    @Override
    public List<String> listCountries() throws IOException {
        String methodName = "listCountries()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> countriesList = Files.readString(Paths
                .get("C:\\Users\\sanyo\\Desktop\\airportData\\countries.csv"))
                .lines().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Countries : {}", countriesList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return countriesList;
    }

    @Override
    public List<String> listNavaids(List<String> navaids) {
        return null;
    }

    public static boolean smallAirport(String airport){
        return airport.contains(AirportType.SMALLAIRPORT.getValue());
    }

    public static boolean largeAirport(String airport){
        return airport.contains(AirportType.LARGEAIRPORT.getValue());
    }

    public static boolean mediumAirport(String airport){
        return airport.contains(AirportType.MEDIUMAIRPORT.getValue());
    }

    public static boolean baloonAirport(String airport){
        return airport.contains(AirportType.BALOONAIRPORT.getValue());
    }

    public static boolean heliport(String airport){
        return airport.contains(AirportType.HELIPORT.getValue());
    }

    public static boolean closed(String airport){
        return airport.contains(AirportType.CLOSED.getValue());
    }

    public static boolean seaplaneBase(String airport){
        return airport.contains(AirportType.SEAPLANEBASE.getValue());
    }

    public static String splitContinents(String s){
        String[] arr = s.split(",");
        return arr[3].replace("\"", "");
    }
}
