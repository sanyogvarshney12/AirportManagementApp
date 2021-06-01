package com.airport;

import com.airport.exception.NoAirportsFoundForContinentException;
import com.airport.exception.NoHeliportFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class AirportManagerTest {

    List<String> mockAirports;
    List<String> emptyAirports;
    List<String> continentsList;


    @Before
    public void init(){
        System.out.println("Executing before test");
        mockAirports = new ArrayList<>();
        emptyAirports = new ArrayList<>();
        continentsList = Arrays.asList("AF", "AN", "AS", "EU", "NA", "OC", "SA");
        mockAirports.add("323361,00AA,small_airport,Aero B Ranch Airport,38.704022,-101.473911,,NA,US,US-KS,Leoti,no,00AA,,,,,");
        mockAirports.add("322658,00CN,heliport,Kitchen Creek Helibase Heliport,32.7273736,-116.4597417,3350,NA,US,US-CA,Pine Valley,no,00CN,,,,,");
        mockAirports.add("329666,CN-0083,large_airport,Guodu Air Base,36.001741,117.63201,,AS,CN,CN-37,Xintai, Tai'an,no,,,,,,");
        mockAirports.add("32753,ZYYY,medium_airport,Shenyang Dongta Airport,41.784401,123.496002,,AS,CN,CN-21,Dadong, Shenyang,no,ZYYY,,,,,");
        mockAirports.add("342102,ZZZW,closed,Scandium City Heliport,69.355287,-138.93931,4,NA,CA,CA-YT,(Old) Scandium City,no,ZZZW,ZYW,YK96,,,");
        mockAirports.add("26363,Z87,seaplane_base,Blinn Lake Seaplane Base,55.2515983581543,-162.7530059814453,50,NA,US,US-AK,Cold Bay,no,Z87,,Z87,,,");
    }

    @After
    public void destroy(){
        System.out.println("Executing before test");
    }

    /**
     *
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    @Test
    public void testListAllAirports() throws IOException, URISyntaxException, InterruptedException {
        IAirportService manager = new AirportManagerImpl();
        int expected = 65562;
        int actual = manager.listAllAirports();
        assertEquals(expected, actual);
    }


    @Test
    public void testFindAirportsByName() {
        AirportManagerImpl manager = new AirportManagerImpl();
        String actual = manager.findAirportByName("Shenyang Dongta Airport", mockAirports);
        assertNotNull(actual);
    }

    @Test
    public void testFindAirportsByCountry() {
        AirportManagerImpl manager = new AirportManagerImpl();
        Object[] expected = mockAirports.stream().filter(a->a.contains("US"))
                .collect(Collectors.toList()).toArray();
        Object[] actual = manager.findAirportByCountry("US", mockAirports).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindAirportsByType(){
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = mockAirports.stream().filter(a->a.contains("heliport"))
                .collect(Collectors.toList()).toArray();
        Object[] actual = manager.findAirportByType("heliport", mockAirports).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFindAirportsBySize() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testFindAirportsByRunways() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testFindHelipads() {
        IAirportService manager = new AirportManagerImpl();
        long expected = 1;
        long actual = manager.findHelipads(mockAirports);
        assertEquals(expected,actual);
    }

    @Test(expected = NoHeliportFoundException.class)
    public void testFindHelipads_Exception() {
        IAirportService manager = new AirportManagerImpl();
        long expected = 100;
        long actual = manager.findHelipads(emptyAirports);
    }

    @Test
    public void testFindAirportsByContinent() {
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = mockAirports.stream().filter(s->s.contains("NA")).toArray();
        Object[] actual = manager.findAirportsByContinent("NA", mockAirports).toArray();
        assertArrayEquals(expected, actual);
    }

    @Test(expected = NoAirportsFoundForContinentException.class)
    public void testFindAirportsByContinent_Exception() {
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = mockAirports.stream().filter(s->s.contains("AF")).toArray();
        Object[] actual = manager.findAirportsByContinent("AF", mockAirports).toArray();
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testListAllRegions() throws IOException {
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = Files.readAllLines(Paths
                .get("C:\\Users\\sanyo\\Desktop\\airportData\\regions.csv"))
                .toArray();
        Object[] actual = manager.listAllRegions().toArray();
        assertArrayEquals(expected, actual);
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testListAllNavaids() throws IOException {
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = Files.readAllLines(Paths
                .get("C:\\Users\\sanyo\\Desktop\\airportData\\navaids.csv"))
                .toArray();
        Object[] actual = manager.listNavaids().toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testListAll() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testGetRandomAirport() {
        fail("Not Yet Impemented");
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testListCountries() throws IOException {
        IAirportService manager = new AirportManagerImpl();
        Object[] expected = Files.readAllLines(Paths
                .get("C:\\Users\\sanyo\\Desktop\\airportData\\countries.csv")).toArray();
        Object[] actual = manager.listCountries().toArray();
        assertArrayEquals(expected, actual);
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testListContinents() throws IOException {
        IAirportService manage = new AirportManagerImpl();
        Object[] expected = continentsList.toArray();
        Object[] actual = manage.listContinents().toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testLogin() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testSignup() {
        fail("Not Yet Impemented");
    }

    public void testHelp(){
        fail("Not Yet Impemented");
    }

    public void listAirportsSorted(){
        fail("Not Yet Impemented");
    }

    public void listAirportsPaginated(){
        fail("Not Yet Impemented");
    }
}
