package org.example.skp2reservationservice.repository;

import org.example.skp2reservationservice.domain.ReservationSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationSlotRepository extends JpaRepository<ReservationSlot, Long> {
}
