package com.airport;

import com.airport.helper.PropertyHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AirportsDataDownloader {
//Using Java11 features
    public static void main(String[] args) throws Exception {

        List<String> airports = readAirportData();
        IAirportService airportManager = new AirportManagerImpl();
        airportManager.listAllAirports(airports);
    }

    public static List<String> readAirportData() throws Exception {
        HttpRequest request = HttpRequest.newBuilder(new URI(PropertyHelper.getProperty("CSV_LOCATION")))
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
