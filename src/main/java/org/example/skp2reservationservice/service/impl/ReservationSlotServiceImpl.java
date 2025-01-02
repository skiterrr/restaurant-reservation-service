package org.example.skp2reservationservice.service.impl;

import org.example.skp2reservationservice.domain.ReservationSlot;
import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.domain.Table;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.example.skp2reservationservice.exception.NotFoundException;
import org.example.skp2reservationservice.mapper.ReservationSlotMapper;
import org.example.skp2reservationservice.repository.ReservationSlotRepository;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.example.skp2reservationservice.repository.TableRepository;
import org.example.skp2reservationservice.service.ReservationSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationSlotServiceImpl implements ReservationSlotService {

    private final ReservationSlotRepository reservationSlotRepository;
    private final ReservationSlotMapper reservationSlotMapper;
    private final TableRepository tableRepository;

    @Autowired
    public ReservationSlotServiceImpl(ReservationSlotRepository reservationSlotRepository, ReservationSlotMapper reservationSlotMapper, TableRepository tableRepository) {
        this.reservationSlotRepository = reservationSlotRepository;
        this.reservationSlotMapper = reservationSlotMapper;
        this.tableRepository = tableRepository;
    }

    @Override
    public Page<ReservationSlotDTO> getAllReservationSlots(Pageable pageable) {
        return reservationSlotRepository.findAll(pageable).map(reservationSlotMapper::reservationSlotToReservationSlotDTO);
    }

    @Override
    public ReservationSlotDTO getReservationSlot(Long id) {
        return reservationSlotRepository.findById(id)
                .map(reservationSlotMapper::reservationSlotToReservationSlotDTO)
                .orElseThrow(() -> new NotFoundException(String.format("Reservation slot with id: %d does not exists.", id)));
    }

    @Override
    public ReservationSlotDTO createReservationSlot(ReservationSlotDTO reservationSlotDTO) {
        ReservationSlot reservationSlot = reservationSlotMapper.reservationSlotDTOToReservationSlot(reservationSlotDTO);
        System.err.println(reservationSlotMapper.reservationSlotToReservationSlotDTO(reservationSlot));
        reservationSlotRepository.save(reservationSlot);
        return reservationSlotMapper.reservationSlotToReservationSlotDTO(reservationSlot);
    }

    @Override
    public ReservationSlotDTO updateReservationSlot(Long id, ReservationSlotDTO reservationSlotDTO) {
        ReservationSlot reservationSlot = reservationSlotRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Reservation slot with id: %d does not exist.", id)));

        reservationSlot.setSlotEnd(reservationSlotDTO.getSlotEnd());
        reservationSlot.setSlotStart(reservationSlotDTO.getSlotStart());
        reservationSlot.setAvailable(reservationSlotDTO.getAvailable());
        reservationSlot.setTable(tableRepository.findById(reservationSlotDTO.getTableId()).orElseThrow(() -> new NotFoundException(String.format("Table with id: %d does not exist.", id))));
        reservationSlot = reservationSlotRepository.save(reservationSlot);

        return reservationSlotMapper.reservationSlotToReservationSlotDTO(reservationSlot);
    }


    @Override
    public void deleteReservationSlot(Long id) {
        reservationSlotRepository.deleteById(id);
    }
}
