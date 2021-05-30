package com.airport;

import java.util.List;

public interface IAirportService {

    public int listAllAirports(List<String> airports);
    public String findAirportByName(String name, List<String> airports);
    public List<String> findAirportByCountry(String country, List<String> airports);
    public List<String> findAirportByAirlines();
    public List<String> findAirportByType(String type, List<String> airports);

}
