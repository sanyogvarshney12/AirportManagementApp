package com.airport.functions;

import com.airport.model.Airport;
import com.airport.model.Country;
import com.airport.model.NavigationAid;
import com.airport.model.Region;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class TransformerFunctions {

    /**
     * Private method to restrict object invocation....
     */
    private TransformerFunctions() {
    }

    /**
     * @param airport
     * @return
     */
    public static Airport stringToAirport(String airport) {
        if (airport == null)
            throw new IllegalArgumentException();
        var datas = airport.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        var airportObject = new Airport();
        if (datas != null && datas.length > 0) {
            airportObject.setId(Long.parseLong(datas[0].replace("\"", "")));
            airportObject.setIdent(datas[1].replace("\"", ""));
            airportObject.setType(datas[2].replace("\"", ""));
            airportObject.setName(datas[3].replace("\"", ""));
            airportObject.setLatitude(Double.parseDouble(datas[4].replace("\"", "")));
            airportObject.setLongitude(Double.parseDouble(datas[5].replace("\"", "")));
            airportObject.setCountryName(datas[10].replace("\"", ""));
            //airportObject.setRegionName(datas[12].replace("\"", ""));
            //airportObject.setMunicipality(datas[13].replace("\"", ""));
        }
        return airportObject;
    }

    /**
     * @param country
     * @return
     */
    public static Country stringToCountry(String country) {
        if (country == null)
            throw new IllegalArgumentException();
        String[] datas = country.split(",");
        var countryObject = new Country();
        if (datas != null && datas.length > 0) {
            countryObject.setId(Long.parseLong(datas[0].replace("\"", "")));
            countryObject.setCode(datas[1].replace("\"", ""));
            countryObject.setName(datas[2].replace("\"", ""));
            countryObject.setContinent(datas[3].replace("\"", ""));
        }
        return countryObject;
    }

    /**
     * @param region
     * @return
     */
    public static Region stringToRegion(String region) {
        if (region == null)
            throw new IllegalArgumentException();
        String[] datas = region.split(",");
        var regionObject = new Region();
        if (datas != null && datas.length > 0) {
            regionObject.setId(Long.parseLong(datas[0].replace("\"", "")));
            regionObject.setCode(datas[1].replace("\"", ""));
            regionObject.setName(datas[3].replace("\"", ""));
            regionObject.setContinent(datas[4].replace("\"", ""));
        }
        return regionObject;
    }


    /**
     * @param navaid
     * @return
     */
    public static NavigationAid stringToNavaid(String navaid) {
        if (navaid == null)
            throw new IllegalArgumentException();
        String[] datas = navaid.split(",");
        var navaidObject = new NavigationAid();
        if (datas != null && datas.length > 0) {
            navaidObject.setId(Long.parseLong(datas[0].replace("\"", "")));
            navaidObject.setName(datas[3].replace("\"", ""));
            navaidObject.setLatitude(Double.parseDouble(datas[6]));
            navaidObject.setLongitude(Double.parseDouble(datas[7]));
        }
        return navaidObject;
    }


}
