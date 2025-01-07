package org.example.skp2reservationservice.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "reservation_slots")
public class ReservationSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    @Column(name = "slot_start")
    private String slotStart;

    @Column(name = "slot_end")
    private String slotEnd;

    @Column(name = "is_available")
    private Boolean isAvailable;

    public ReservationSlot(Long id, Table table, String slotStart, String slotEnd, Boolean isAvailable) {
        this.id = id;
        this.table = table;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.isAvailable = isAvailable;
    }

    public ReservationSlot(Table table, String slotStart, String slotEnd, Boolean isAvailable) {
        this.table = table;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.isAvailable = isAvailable;
    }

    public ReservationSlot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(String slotStart) {
        this.slotStart = slotStart;
    }

    public String getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(String slotEnd) {
        this.slotEnd = slotEnd;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "ReservationSlot{" +
                "id=" + id +
                ", table=" + table +
                ", slotStart=" + slotStart +
                ", slotEnd=" + slotEnd +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
