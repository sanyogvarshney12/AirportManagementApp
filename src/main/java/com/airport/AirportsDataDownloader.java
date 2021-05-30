package com.airport;

import com.airport.helper.PropertyHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AirportsDataDownloader {
//Using Java11 features
    public static void main(String[] args) throws Exception {

        //List<String> airports = readAirportData();
        IAirportService airportManager = new AirportManagerImpl();
       // airportManager.listAllAirports(airports);
    }



}
