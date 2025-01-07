package org.example.skp2reservationservice.dto;

import org.example.skp2reservationservice.domain.Table;

import java.time.LocalDateTime;

public class ReservationSlotDTO {

    private Long id;
    private Long tableId;
    private String slotStart;
    private String slotEnd;
    private Boolean Available;

    public ReservationSlotDTO() {}

    public ReservationSlotDTO(Long id, Long tableId, String slotStart, String slotEnd, Boolean available) {
        this.id = id;
        this.tableId = tableId;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        Available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
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
        return Available;
    }

    public void setAvailable(Boolean isAvailable) {
        this.Available = isAvailable;
    }

}
