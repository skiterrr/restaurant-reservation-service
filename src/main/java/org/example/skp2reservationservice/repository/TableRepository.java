package org.example.skp2reservationservice.repository;

import org.example.skp2reservationservice.domain.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<Table, Long> {
}
