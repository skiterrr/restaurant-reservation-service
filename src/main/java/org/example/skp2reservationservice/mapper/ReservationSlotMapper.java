package org.example.skp2reservationservice.mapper;

import org.example.skp2reservationservice.domain.ReservationSlot;
import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.domain.Table;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.example.skp2reservationservice.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationSlotMapper {

    private TableRepository tableRepository;

    public ReservationSlotMapper() {
    }

    @Autowired
    public ReservationSlotMapper(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public ReservationSlotDTO reservationSlotToReservationSlotDTO(ReservationSlot reservationSlot) {
        ReservationSlotDTO reservationSlotDTO = new ReservationSlotDTO();
        reservationSlotDTO.setId(reservationSlot.getId());
        reservationSlotDTO.setSlotEnd(reservationSlot.getSlotEnd());
        reservationSlotDTO.setSlotStart(reservationSlot.getSlotStart());
        reservationSlotDTO.setAvailable(reservationSlot.getAvailable());
        reservationSlotDTO.setTableId(reservationSlot.getTable().getId());
        return reservationSlotDTO;
    }

    public ReservationSlot reservationSlotDTOToReservationSlot(ReservationSlotDTO reservationSlotDTO) {
        ReservationSlot reservationSlot = new ReservationSlot();
        reservationSlot.setId(reservationSlotDTO.getId());
        reservationSlot.setSlotEnd(reservationSlotDTO.getSlotEnd());
        reservationSlot.setSlotStart(reservationSlotDTO.getSlotStart());
        reservationSlot.setAvailable(reservationSlotDTO.getAvailable());
        Table table = tableRepository.findById(reservationSlotDTO.getTableId()).orElse(null);
        if (table != null) {
            System.err.println("tableid: "+table.getId());
        }
        reservationSlot.setTable(table);
        return reservationSlot;
    }

}
