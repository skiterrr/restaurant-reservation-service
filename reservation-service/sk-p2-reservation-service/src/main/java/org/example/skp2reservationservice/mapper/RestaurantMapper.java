package org.example.skp2reservationservice.mapper;

import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {

    public RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setDescription(restaurant.getDescription());
        restaurantDTO.setCuisineType(restaurant.getCuisineType());
        restaurantDTO.setNumberOfTables(restaurant.getNumberOfTables());
        restaurantDTO.setWorkingHours(restaurant.getWorkingHours());
        return restaurantDTO;
    }

    public Restaurant restaurantDTOToRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setCuisineType(restaurantDTO.getCuisineType());
        restaurant.setNumberOfTables(restaurantDTO.getNumberOfTables());
        restaurant.setWorkingHours(restaurantDTO.getWorkingHours());
        return restaurant;
    }

}
