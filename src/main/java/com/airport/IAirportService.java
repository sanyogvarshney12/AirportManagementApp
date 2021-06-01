package com.airport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public interface IAirportService {

    public int listAllAirports() throws IOException, URISyntaxException, InterruptedException;
    public String findAirportByName(String name, List<String> airports);
    public List<String> findAirportByCountry(String country, List<String> airports);
    public List<String> findAirportByType(String type, List<String> airports);
    public long findHelipads(List<String> airports);
    public List<String> findAirportsByContinent(String continent, List<String> airports);
    public List<String> listContinents() throws IOException;
    public List<String> listCountries() throws IOException;
    public List<String> listAllRegions() throws IOException;
    public List<String> listNavaids() throws IOException;
    public String randomAirport(List<String> airports);

}
