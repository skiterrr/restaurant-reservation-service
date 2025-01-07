package org.example.skp2reservationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.skp2reservationservice.dto.ReservationDTO;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.example.skp2reservationservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Make a reservation")
    @PostMapping
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<ReservationDTO> createReservationSlot(@RequestBody ReservationDTO reservationDTO) {
        reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Cancel a reservation by client")
    @PutMapping("/{id}")
//    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<ReservationDTO> clientCancelReservation( @PathVariable("id") long id) {
        return new ResponseEntity<>(reservationService.clientCancelReservation(id), HttpStatus.OK);
    }
}
