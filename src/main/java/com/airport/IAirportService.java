package com.airport;

import java.io.IOException;
import java.util.List;

public interface IAirportService {

    public int listAllAirports(List<String> airports);
    public String findAirportByName(String name, List<String> airports);
    public List<String> findAirportByCountry(String country, List<String> airports);
    public List<String> findAirportByType(String type, List<String> airports);
    public long findHelipads(List<String> airports);
    public List<String> findAirportsByContinent(String continent, List<String> airports);
    public List<String> listContinents(List<String> continents);
    public List<String> listCountries() throws IOException;
    public List<String> listNavaids(List<String> navaids);

}
