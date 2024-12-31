package org.example.skp2reservationservice.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.security.CheckSecurity;
import org.example.skp2reservationservice.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "Get all restaurantsss")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<Page<RestaurantDTO>> getAllRestaurants(
            @RequestHeader("Authorization") String authorization,
            @Parameter(description = "Page number", example = "0") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", example = "10") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort criteria", example = "name,asc") @RequestParam(defaultValue = "id,asc") String sort,
            Pageable pageable) {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
    public ResponseEntity<RestaurantDTO> getRestaurantById(@RequestHeader("Authorization") String authorization, @PathVariable("id") long id) {
        return new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestHeader("Authorization") String authorization, @RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestHeader("Authorization") String authorization, @PathVariable("id") long id, @RequestBody RestaurantDTO restaurantDTO) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(id, restaurantDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> deleteRestaurant(@RequestHeader("Authorization") String authorization, @PathVariable("id") long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
