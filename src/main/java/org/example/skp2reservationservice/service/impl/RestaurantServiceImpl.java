package org.example.skp2reservationservice.service.impl;

import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.exception.NotFoundException;
import org.example.skp2reservationservice.mapper.RestaurantMapper;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.example.skp2reservationservice.service.RestaurantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;
    private RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Override
    public Page<RestaurantDTO> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable).map(restaurantMapper::restaurantToRestaurantDTO);
    }

    @Override
    public RestaurantDTO getRestaurantById(long id) {
        return restaurantRepository.findById(id)
                .map(restaurantMapper::restaurantToRestaurantDTO)
                .orElseThrow(() -> new NotFoundException(String.format("Movie with id: %d does not exists.", id)));
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantMapper.restaurantDTOToRestaurant(restaurantDTO);
        restaurantRepository.save(restaurant);
        return restaurantMapper.restaurantToRestaurantDTO(restaurant);
    }

    @Override
    public RestaurantDTO updateRestaurant(long id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Restaurant with id: %d does not exist.", id)));

        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setDescription(restaurantDTO.getDescription());
        restaurant.setNumberOfTables(restaurantDTO.getNumberOfTables());
        restaurant.setWorkingHours(restaurantDTO.getWorkingHours());
        restaurant.setCuisineType(restaurantDTO.getCuisineType());

        restaurant = restaurantRepository.save(restaurant);

        return restaurantMapper.restaurantToRestaurantDTO(restaurant);
    }

    @Override
    public void deleteRestaurant(long id) {
        restaurantRepository.deleteById(id);
    }
}
