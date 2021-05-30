package com.airport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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

}
