package org.example.skp2reservationservice.mapper;

import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.domain.Table;
import org.example.skp2reservationservice.dto.RestaurantDTO;
import org.example.skp2reservationservice.dto.TableDTO;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.example.skp2reservationservice.repository.TableRepository;
import org.example.skp2reservationservice.service.impl.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableMapper {

    private RestaurantMapper restaurantMapper;
    private RestaurantRepository restaurantRepository;
    private RestaurantServiceImpl restaurantService;

    public TableMapper() {
    }
    @Autowired
    public TableMapper(RestaurantMapper restaurantMapper, RestaurantRepository restaurantRepository, RestaurantServiceImpl restaurantService) {
        this.restaurantMapper = restaurantMapper;
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
    }

    public TableDTO tableToTableDTO(Table table) {
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(table.getId());
        tableDTO.setRestaurantId(table.getRestaurant().getId());
        tableDTO.setZone(table.getZone());
        tableDTO.setSeatsNumber(table.getSeatsNumber());
        return tableDTO;
    }

    public Table tableDTOToTable(TableDTO tableDTO) {
        Table table = new Table();
        Restaurant restaurant = restaurantRepository.findById(tableDTO.getRestaurantId()).orElse(null);
        if (restaurant != null) {
            System.err.println(restaurant.getId());
        }
        table.setRestaurant(restaurant);
        table.setZone(tableDTO.getZone());
        table.setSeatsNumber(tableDTO.getSeatsNumber());
        return table;
    }

}
