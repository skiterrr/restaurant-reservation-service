package org.example.skp2reservationservice.repository;

import org.example.skp2reservationservice.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
