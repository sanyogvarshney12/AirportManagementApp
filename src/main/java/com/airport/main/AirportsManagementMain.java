package com.airport.main;

import com.airport.db.AirportsInMemoryDB;
import com.airport.model.Airport;

import java.util.List;

public class AirportsManagementMain {

    public static void main(String[] args) {
        List<Airport> airports = AirportsInMemoryDB.airports();
        System.out.println(airports.size());
    }
}
