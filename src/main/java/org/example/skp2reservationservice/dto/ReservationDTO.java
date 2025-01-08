package org.example.skp2reservationservice.dto;

import org.example.skp2reservationservice.domain.ReservationStatus;

import java.time.LocalDateTime;

public class ReservationDTO {
    private Long id;
    private Long reservationSlotId;
    private Long userId;
    private ReservationStatus status;
    private LocalDateTime createdOn;
    private String email;

    // Constructors
    public ReservationDTO() {}


    public ReservationDTO(Long id, Long reservationSlotId, Long userId, ReservationStatus status) {
        this.id = id;
        this.reservationSlotId = reservationSlotId;
        this.userId = userId;
        this.status = status;
    }

    public ReservationDTO(Long id, Long reservationSlotId, Long userId, ReservationStatus status, LocalDateTime createdOn,String email) {
        this.id = id;
        this.reservationSlotId = reservationSlotId;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
        this.email = email;
    }

    public ReservationDTO(Long reservationSlotId, Long userId, ReservationStatus status, LocalDateTime createdOn) {
        this.reservationSlotId = reservationSlotId;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
    }

    // Getters and Setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReservationSlotId() {
        return reservationSlotId;
    }

    public void setReservationSlotId(Long reservationSlotId) {
        this.reservationSlotId = reservationSlotId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
