package org.example.skp2reservationservice.repository;

import org.example.skp2reservationservice.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
