package org.example.skp2reservationservice.service;

import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {

    Page<RestaurantDTO> getAllRestaurants(Pageable pageable);
    RestaurantDTO getRestaurantById(long id);
    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);
    RestaurantDTO updateRestaurant(long id, RestaurantDTO restaurantDTO);
    void deleteRestaurant(long id);
}
