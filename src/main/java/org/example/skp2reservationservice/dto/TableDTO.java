package org.example.skp2reservationservice.dto;

import org.example.skp2reservationservice.domain.TableZone;

public class TableDTO {
    private Long id;
    private Long restaurantId;
    private int seatsNumber;
    private TableZone zone;

    // Constructors
    public TableDTO() {
    }

    public TableDTO(Long id, int seatsNumber, TableZone zone) {
        this.id = id;
        this.seatsNumber = seatsNumber;
        this.zone = zone;
    }

    public TableDTO(Long id, Long restaurantId, int seatsNumber, TableZone zone) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.seatsNumber = seatsNumber;
        this.zone = zone;
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
