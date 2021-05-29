package com.airport;

import com.airport.helper.PropertyHelper;

import java.io.BufferedReader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AirportsDataDownloader {
//Using Java11 features
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome");

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



        AirportManager airportManager = new AirportManager();
        airportManager.listAllAirports(airports);
    }

}
