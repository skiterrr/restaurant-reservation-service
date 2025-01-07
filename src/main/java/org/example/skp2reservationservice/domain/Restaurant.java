package org.example.skp2reservationservice.domain;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 500)
    private String description;

    @Column(name = "number_of_tables")
    private int numberOfTables;

    @Column(name = "working_hours", length = 50)
    private String workingHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type", length = 50)
    private CuisineType cuisineType;

    @OneToMany(mappedBy = "restaurant")
    private List<Table> tables;

    // Constructors
    public Restaurant() {
    }

    public Restaurant(String name, String address, String description, int numberOfTables, String workingHours, CuisineType cuisineType) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.numberOfTables = numberOfTables;
        this.workingHours = workingHours;
        this.cuisineType = cuisineType;
    }

    // Getters and Setters
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

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant_id=" + id  +
                '}';
    }
}
