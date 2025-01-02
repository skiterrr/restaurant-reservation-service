package org.example.skp2reservationservice.service.impl;

import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.domain.Table;
import org.example.skp2reservationservice.dto.TableDTO;
import org.example.skp2reservationservice.exception.NotFoundException;
import org.example.skp2reservationservice.mapper.TableMapper;
import org.example.skp2reservationservice.repository.TableRepository;
import org.example.skp2reservationservice.service.TableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    private TableRepository tableRepository;
    private TableMapper tableMapper;

    public TableServiceImpl(TableRepository tableRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;
    }

    @Override
    public Page<TableDTO> getAllTables(Pageable pageable) {
        return tableRepository.findAll(pageable).map(tableMapper::tableToTableDTO);
    }

    @Override
    public TableDTO getTableByID(long id) {
        return tableRepository.findById(id)
                .map(tableMapper::tableToTableDTO)
                .orElseThrow(() -> new NotFoundException(String.format("Table with id: %d does not exists.", id)));
    }

    @Override
    public TableDTO createTable(TableDTO tableDTO) {
        Table table = tableMapper.tableDTOToTable(tableDTO);
        tableRepository.save(table);
        return tableMapper.tableToTableDTO(table);
    }

    @Override
    public TableDTO updateTable(long id, TableDTO tableDTO) {
        Table table = tableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Restaurant with id: %d does not exist.", id)));

        table.setZone(tableDTO.getZone());
        table.setSeatsNumber(tableDTO.getSeatsNumber());

        table = tableRepository.save(table);

        return tableMapper.tableToTableDTO(table);
    }

    @Override
    public void deleteTable(long id) {
        tableRepository.deleteById(id);
    }
}
