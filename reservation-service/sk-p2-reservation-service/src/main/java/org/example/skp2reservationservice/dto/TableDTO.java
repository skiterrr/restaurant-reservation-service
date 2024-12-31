package org.example.skp2reservationservice.dto;

public class TableDTO {
    private Long id;
    private Long restaurantId;
    private int seatsNumber;
    private String zone;

    public TableDTO() {}

    public TableDTO(Long id, Long restaurantId, int seatsNumber, String zone) {
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
