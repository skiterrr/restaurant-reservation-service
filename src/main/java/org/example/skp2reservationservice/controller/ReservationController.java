package org.example.skp2reservationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.skp2reservationservice.dto.ReservationDTO;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.example.skp2reservationservice.security.CheckSecurity;
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
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<ReservationDTO> createReservation(@RequestHeader(value = "Authorization") String authorization,
                                                            @RequestBody ReservationDTO reservationDTO) {
        reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Cancel a reservation by client")
    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_USER"})
    public ResponseEntity<ReservationDTO> clientCancelReservation(@RequestHeader(value = "Authorization") String authorization,
                                                                  @PathVariable("id") long id) {
        return new ResponseEntity<>(reservationService.clientCancelReservation(id), HttpStatus.OK);
    }

//    @Operation(summary = "Cancel a reservation by manager")
//    @PutMapping("/{id}")
//    @CheckSecurity(roles = {"ROLE_MANAGER"})
//    public ResponseEntity<ReservationDTO> managerCancelReservation(@RequestHeader(value = "Authorization") String authorization,
//                                                                  @PathVariable("id") long id) {
//        return new ResponseEntity<>(reservationService.managerCancelReservation(id), HttpStatus.OK);
//    }
}
