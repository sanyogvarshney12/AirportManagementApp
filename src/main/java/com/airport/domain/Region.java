package com.airport.domain;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class Region {
    private Long id;
    private String code;
    private String name;
    private String continent;
    private String isoCountry;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getCode() {
        return code;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }
}
