package org.example.skp2reservationservice.service;


import org.example.skp2reservationservice.domain.ReservationSlot;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationSlotService {

    Page<ReservationSlotDTO> getAllReservationSlots(Pageable pageable);
    ReservationSlotDTO getReservationSlot(Long id);
    ReservationSlotDTO createReservationSlot(ReservationSlotDTO reservationSlotDTO);
    ReservationSlotDTO updateReservationSlot(Long id, ReservationSlotDTO reservationSlotDTO);
    void deleteReservationSlot(Long id);

}
