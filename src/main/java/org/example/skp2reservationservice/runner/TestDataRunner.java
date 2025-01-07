package org.example.skp2reservationservice.runner;

import org.example.skp2reservationservice.domain.*;
import org.example.skp2reservationservice.repository.ReservationRepository;
import org.example.skp2reservationservice.repository.ReservationSlotRepository;
import org.example.skp2reservationservice.repository.RestaurantRepository;
import org.example.skp2reservationservice.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RestaurantRepository restaurantRepository;
    private TableRepository tableRepository;
    private ReservationSlotRepository reservationSlotRepository;

    @Autowired
    public TestDataRunner(RestaurantRepository restaurantRepository, TableRepository tableRepository, ReservationSlotRepository reservationSlotRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
        this.reservationSlotRepository = reservationSlotRepository;
    }

    public TestDataRunner() {
    }

    @Override
    public void run(String... args) throws Exception {
        Restaurant restaurant = new Restaurant("restoran1","abv 15",
                "nesto",15,"9-17", CuisineType.ITALIAN);
        Restaurant restaurant1 = new Restaurant("restoran2","bcc 16",
                "nestooo",12,"9-22", CuisineType.CHINESE);
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant1);

        Table table = new Table(4, TableZone.INSIDE,restaurant);
        Table table1 = new Table(6, TableZone.OUTSIDE,restaurant);
        Table table2 = new Table(5, TableZone.SMOKING,restaurant1);
        tableRepository.save(table);
        tableRepository.save(table1);
        tableRepository.save(table2);

        List<Table> tables = tableRepository.findAll();
        List<Table>rTables = new ArrayList<>();
        for (Table t : tables) {
            if(t.getRestaurant().getId().equals(restaurant.getId())){
                rTables.add(t);
            }
        }
        restaurant.setTables(rTables);
        List<Table>r1Tables = new ArrayList<>();
        for (Table t : tables) {
            if(t.getRestaurant().getId().equals(restaurant1.getId())){
                r1Tables.add(t);
            }
        }
        restaurant1.setTables(r1Tables);
        System.err.println(tables);

        ReservationSlot reservationSlot = new ReservationSlot(table, "17:30","19:30",true);
        ReservationSlot reservationSlot1 = new ReservationSlot(table1, "13:30","15:30",true);
        ReservationSlot reservationSlot2 = new ReservationSlot(table1, "15:45","18:00",true);
        reservationSlotRepository.save(reservationSlot);
        reservationSlotRepository.save(reservationSlot1);
        reservationSlotRepository.save(reservationSlot2);

        System.err.println(reservationSlotRepository.findAll());

    }

}
