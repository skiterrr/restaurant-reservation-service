package org.example.skp2reservationservice.service;

import org.example.skp2reservationservice.dto.ReservationDTO;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;

public interface ReservationService {

    void createReservation(ReservationDTO reservationDTO);
    ReservationDTO clientCancelReservation(long id);
    ReservationDTO managerCancelReservation(long id);

}
