package com.airport;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AirportManagerTest {

    IAirportService mockService;
    List<String> mockAirports = new ArrayList<>();


    @Before
    public void init(){
        System.out.println("Executing before test");
        mockService = Mockito.mock(IAirportService.class);
        mockAirports.add("38612,YTNC,small_airport,Tuncurry Airport,-32.150001525878906,152.48300170898438,,OC,AU,AU-NSW,,no,YTNC,,,,,");
        mockAirports.add("38612,YTNC,small_airport,Tuncurry Airport,-32.150001525878906,152.48300170898438,,OC,AU,AU-NSW,,no,YTNC,,,,,");
        mockAirports.add("38612,YTNC,small_airport,Tuncurry Airport,-32.150001525878906,152.48300170898438,,OC,AU,AU-NSW,,no,YTNC,,,,,");
    }

    @After
    public void destroy(){
        System.out.println("Executing before test");
    }

    @Test
    public void testListAllAirports(){
        IAirportService manager = new AirportManager();
        int expected = 3;
        int actual = manager.listAllAirports(mockAirports);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindAirportsByName() {
        AirportManager manager = new AirportManager();
        int expected = 3;
        int actual = manager.findAirportByName();
    }

    @Test
    public void testFindAirportsByCountry() {
        fail("Not Yet Impemented");
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
        fail("Not Yet Impemented");
    }

    @Test
    public void testFindAirportsByContinent() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testListAllRegions() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testListAllNavaids() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testListAll() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testGetRandomAirport() {
        fail("Not Yet Impemented");
    }
    @Test
    public void testListCountries() {
        fail("Not Yet Impemented");
    }

    @Test
    public void testListContinents() {
        fail("Not Yet Impemented");
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
