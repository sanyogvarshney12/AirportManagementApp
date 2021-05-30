package com.airport.constants;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 * @apiNote enum constants
 */
public enum AirportType {
    SMALLAIRPORT("small_airport"),
    LARGEAIRPORT("large_airport"),
    MEDIUMAIRPORT("medium_airport"),
    BALOONAIRPORT("baloon_airport"),
    CLOSED("closed"),
    HELIPORT("heliport"),
    SEAPLANEBASE("seaplane_base");

    private final String value;
    private AirportType(String value){
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return this.value;
    }
}
