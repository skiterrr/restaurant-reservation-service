package org.example.skp2reservationservice.domain;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@jakarta.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "seats_number")
    private int seatsNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TableZone zone;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Table() {
    }

    public Table(Long id, int seatsNumber, TableZone zone) {
        this.id = id;
        this.seatsNumber = seatsNumber;
        this.zone = zone;
    }

    public Table(Long id, int seatsNumber, TableZone zone, Restaurant restaurant) {
        this.id = id;
        this.seatsNumber = seatsNumber;
        this.zone = zone;
        this.restaurant = restaurant;
    }

    public Table(int seatsNumber, TableZone zone) {
        this.seatsNumber = seatsNumber;
        this.zone = zone;
    }

    public Table(int seatsNumber, TableZone zone, Restaurant restaurant) {
        this.seatsNumber = seatsNumber;
        this.zone = zone;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public TableZone getZone() {
        return zone;
    }

    public void setZone(TableZone zone) {
        this.zone = zone;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
