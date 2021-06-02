package com.airport;

import com.airport.constants.AirportType;
import com.airport.exception.NoAirportsFoundForContinentException;
import com.airport.exception.NoHeliportFoundException;
import com.airport.helper.PropertyHelper;
import com.airport.logger.ApplicationLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class AirportManagerImpl implements IAirportService{

    private static final String CLASSNAME = AirportManagerImpl.class.getName();
    private static final Logger log = LoggerFactory.getLogger(AirportManagerImpl.class);
    private static final ApplicationLogger logger = new ApplicationLogger();
    private static final String METHODSTARTMSG = "***** Method Started *****";
    private static final String METHODENDMSG = "***** Method Ended *****";

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            logger.error("error", e);
        }
    }

    /**
     *
     * @return int
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public int listAllAirports() throws IOException, URISyntaxException, InterruptedException {
        String methodName = "listAllAirports()";
        log.debug(CLASSNAME, methodName, METHODSTARTMSG);
        int totalAirports = readAirportData().size();
        logger.debug(CLASSNAME, methodName, "For people who fly: {} and counting...", totalAirports);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return totalAirports;
    }

    /**
     *
     * @param name
     * @param airports
     * @return String
     */
    public String findAirportByName(String name, List<String> airports) {
        String methodName = "findAirportByName()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByName = getListByFunction(airports, name);
        logger.debug(CLASSNAME, methodName, "Airport Details by Name : {}", airportByName.get(0));
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByName.get(0);
    }

    /**
     *
     * @param country
     * @param airports
     * @return List
     */
    public List<String> findAirportByCountry(String country, List<String> airports) {
        String methodName = "findAirportByCountry()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByCountry = getListByFunction(airports, country);
        logger.debug(CLASSNAME, methodName, "Airport Details by Country : {}", airportByCountry);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByCountry;
    }

    /**
     *
     * @param type
     * @param airports
     * @return List
     */
    public List<String> findAirportByType(String type, List<String> airports) {
        String methodName = "findAirportByType()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByType = getListByFunction(airports, type);
        logger.debug(CLASSNAME, methodName, "Airport Details by Type : {}", airportByType);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByType;
    }

    /**
     *
     * @param airports
     * @return long
     */
    public long findHelipads(List<String> airports) {
        String methodName = "findHelipads()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> helipadsList = airports.stream().filter(AirportManagerImpl::heliport)
                .collect(Collectors.toList());
        if(helipadsList.isEmpty()){
            throw new NoHeliportFoundException("No Heliports found in the system.");
        }
        long size = helipadsList.size();
        logger.debug(CLASSNAME, methodName, "Number of Heliports found : {}", size);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return size;
    }

    /**
     *
     * @param continent
     * @param airports
     * @return List
     */
    public List<String> findAirportsByContinent(String continent, List<String> airports) {
        String methodName = "findAirportsByContinent()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> airportByContinent = getListByFunction(airports, continent);
        if(airportByContinent.isEmpty()){
            throw new NoAirportsFoundForContinentException("No Airport found for this Continent");
        }
        logger.debug(CLASSNAME, methodName, "Airport Details by Continents : {}", airportByContinent);
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return airportByContinent;
    }

    /**
     *
     * @return List
     * @throws IOException
     */
    @Override
    public List<String> listContinents() throws IOException {
        String methodName = "listContinents()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> countriesList = Files.readString(Paths
                .get(PropertyHelper.getProperty("COUNTRIES_CSV_LOCATION")))
                .lines().collect(Collectors.toList());
        countriesList.remove(0);
        List<String> continentList = countriesList.stream().map(AirportManagerImpl::splitContinents).distinct().sorted().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Continents : {}", continentList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return continentList;
    }

    /**
     *
     * @return List
     * @throws IOException
     */
    @Override
    public List<String> listCountries() throws IOException {
        String methodName = "listCountries()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> countriesList = Files.readString(Paths
                .get(PropertyHelper.getProperty("COUNTRIES_CSV_LOCATION")))
                .lines().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Countries : {}", countriesList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return countriesList;
    }

    /**
     *
     * @return List
     * @throws IOException
     */
    public List<String> listAllRegions() throws IOException {
        String methodName = "listAllRegions()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> regionList = Files.readString(Paths
                .get(PropertyHelper.getProperty("REGION_CSV_LOCATION")))
                .lines().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Regions : {}", regionList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return regionList;
    }

    /**
     *
     * @return List
     * @throws IOException
     */
    @Override
    public List<String> listNavaids() throws IOException {
        String methodName = "listNavaids()";
        logger.debug(CLASSNAME, methodName, METHODSTARTMSG);
        List<String> navaidsList = Files.readString(Paths
                .get(PropertyHelper.getProperty("NAVAIDS_CSV_LOCATION")))
                .lines().collect(Collectors.toList());
        logger.debug(CLASSNAME, methodName, "Total Navigation Aids : {}", navaidsList.size());
        logger.debug(CLASSNAME, methodName, METHODENDMSG);
        return navaidsList;
    }

    @Override
    public String randomAirport(List<String> airports) {
       return airports.get(random.nextInt());
    }

    /**
     *
     * @param airport
     * @return boolean
     */
    public static boolean heliport(String airport){
        return airport.contains(AirportType.HELIPORT.getValue());
    }

    /**
     *
     * @param s
     * @return String
     */
    public static String splitContinents(String s){
        String[] arr = s.split(",");
        return arr[3].replace("\"", "");
    }

    /**
     *
     * @param airports
     * @param function
     * @return List
     */
    public List<String> getListByFunction(List<String> airports, String function) {
        return airports.stream().filter(airport->airport.contains(function))
                .collect(Collectors.toList());
    }

    /**
     *
     * @return List
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    public static List<String> readAirportData() throws IOException, URISyntaxException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI(PropertyHelper.getProperty("AIRPORTS_CSV_LOCATION")))
                .GET()
                .timeout(Duration.ofMinutes(1))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        BufferedReader reader = new BufferedReader(new StringReader(response.body()));
        String line = "";
        List<String> airports = new ArrayList<>();
        while((line = reader.readLine()) != null){
            airports.add(line.replace("\"", ""));
        }
        return airports;
    }
}
