package org.example.skp2reservationservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.skp2reservationservice.dto.ReservationSlotDTO;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.dto.TableDTO;
import org.example.skp2reservationservice.security.CheckSecurity;
import org.example.skp2reservationservice.service.ReservationSlotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/reservation_slot")
public class ReservationSlotController {

    private final ReservationSlotService reservationSlotService;

    public ReservationSlotController(ReservationSlotService reservationSlotService) {
        this.reservationSlotService = reservationSlotService;
    }

    @Operation(summary = "Get all reservation slots")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<ReservationSlotDTO>> findAll(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(reservationSlotService.getAllReservationSlots(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get a specific reservation slot")
    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<ReservationSlotDTO> getReservationSlotByID(@PathVariable("id") long id) {
        return new ResponseEntity<>(reservationSlotService.getReservationSlot(id), HttpStatus.OK);
    }

    @Operation(summary = "Make a reservation slot")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<ReservationSlotDTO> createReservationSlot(@RequestBody ReservationSlotDTO reservationSlotDTO) {
        return new ResponseEntity<>(reservationSlotService.createReservationSlot(reservationSlotDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a reservation slot")
    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<ReservationSlotDTO> updateReservationSlot( @PathVariable("id") long id, @RequestBody ReservationSlotDTO reservationSlotDTO) {
        return new ResponseEntity<>(reservationSlotService.updateReservationSlot(id, reservationSlotDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete a reservation slot")
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> deleteReservationSlot(@PathVariable("id") long id) {
        reservationSlotService.deleteReservationSlot(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
