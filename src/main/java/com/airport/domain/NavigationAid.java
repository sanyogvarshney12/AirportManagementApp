package com.airport.domain;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class NavigationAid {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
