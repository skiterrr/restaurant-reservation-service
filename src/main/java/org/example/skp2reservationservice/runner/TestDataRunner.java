package org.example.skp2reservationservice.runner;

import org.example.skp2reservationservice.domain.CuisineType;
import org.example.skp2reservationservice.domain.Restaurant;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RestaurantRepository restaurantRepository;

    public TestDataRunner(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Restaurant restaurant = new Restaurant("restoran1","abv 15",
                "nesto",15,"9-17", CuisineType.ITALIAN);
        Restaurant restaurant1 = new Restaurant("restoran2","bcc 16",
                "nestooo",12,"9-22", CuisineType.CHINESE);
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);
    }

}
