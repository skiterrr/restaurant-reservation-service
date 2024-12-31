package org.example.skp2reservationservice.dto;

import org.example.skp2reservationservice.domain.CuisineType;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String description;
    private int numberOfTables;
    private String workingHours;
    private CuisineType cuisineType;

    public RestaurantDTO() {}

    public RestaurantDTO(Long id, String name, String address, String description, int numberOfTables, String workingHours, CuisineType cuisineType) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.numberOfTables = numberOfTables;
        this.workingHours = workingHours;
        this.cuisineType = cuisineType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }
}
