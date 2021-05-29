package com.airport;

import com.airport.constants.AirportType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class AirportManager implements IAirportService{

    public AirportManager(){

    }

    public int listAllAirports(List<String> airports){
        List<String> smallAirports = airports.stream().filter(airport->smallAirport(airport))
                .collect(Collectors.toList());
        smallAirports.stream().forEach(System.out::println);
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
        System.out.println("Total Airports found : "+totalAirports);
        return totalAirports;
    }

    public int findAirportByName() {
        return 100;
    }

    /**
     *
     * @return
     */
    public int findAirportByCountry() {
        return 100;
    }

    public int findAirportByAirlines() {
        return 100;
    }

    public int findAirportByType() {
        return 100;
    }

    public boolean smallAirport(String airport){
        return airport.contains(AirportType.SMALLAIRPORT.getValue());
    }

    public boolean largeAirport(String airport){
        return airport.contains(AirportType.LARGEAIRPORT.getValue());
    }

    public boolean mediumAirport(String airport){
        return airport.contains(AirportType.MEDIUMAIRPORT.getValue());
    }

    public boolean baloonAirport(String airport){
        return airport.contains(AirportType.BALOONAIRPORT.getValue());
    }

    public boolean heliport(String airport){
        return airport.contains(AirportType.HELIPORT.getValue());
    }

    public boolean closed(String airport){
        return airport.contains(AirportType.CLOSED.getValue());
    }

    public boolean seaplaneBase(String airport){
        return airport.contains(AirportType.SEAPLANEBASE.getValue());
    }
}
