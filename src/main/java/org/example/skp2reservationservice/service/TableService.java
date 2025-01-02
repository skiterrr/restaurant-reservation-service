package org.example.skp2reservationservice.service;


import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.dto.TableDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TableService {

    Page<TableDTO> getAllTables(Pageable pageable);
    TableDTO getTableByID(long id);
    TableDTO createTable(TableDTO tableDTO);
    TableDTO updateTable(long id, TableDTO tableDTO);
    void deleteTable(long id);

}
