package org.example.skp2reservationservice.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservation_slot", nullable = false)
    private ReservationSlot reservationSlot;

    private Long userId;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ReservationStatus status;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    // Constructors
    public Reservation() {
    }

    public Reservation(Long id, ReservationSlot reservationSlot, Long userId, ReservationStatus status, LocalDateTime createdOn) {
        this.id = id;
        this.reservationSlot = reservationSlot;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
    }

    public Reservation(ReservationSlot reservationSlot, Long userId, ReservationStatus status, LocalDateTime createdOn,String email) {
        this.reservationSlot = reservationSlot;
        this.userId = userId;
        this.status = status;
        this.createdOn = createdOn;
        this.email = email;
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

    public ReservationSlot getReservationSlot() {
        return reservationSlot;
    }

    public void setReservationSlot(ReservationSlot reservationSlot) {
        this.reservationSlot = reservationSlot;
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
