package org.example.skp2reservationservice.service.impl;

import org.example.skp2reservationservice.client.users.dto.ClientDto;
import org.example.skp2reservationservice.domain.Reservation;
import org.example.skp2reservationservice.domain.ReservationSlot;
import org.example.skp2reservationservice.domain.ReservationStatus;
import org.example.skp2reservationservice.dto.NotificationDTO;
import org.example.skp2reservationservice.dto.ReservationDTO;
import org.example.skp2reservationservice.exception.NotFoundException;
import org.example.skp2reservationservice.repository.ReservationRepository;
import org.example.skp2reservationservice.repository.ReservationSlotRepository;
import org.example.skp2reservationservice.service.NotificationSender;
import org.example.skp2reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepository reservationRepository;
    private ReservationSlotRepository reservationSlotRepository;
    private RestTemplate userServiceRestTemplate;
    @Autowired
    private NotificationSender notificationSender;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationSlotRepository reservationSlotRepository, RestTemplate userServiceRestTemplate) {
        this.reservationRepository = reservationRepository;
        this.reservationSlotRepository = reservationSlotRepository;
        this.userServiceRestTemplate = userServiceRestTemplate;
    }

    @Override
    public void createReservation(ReservationDTO reservationDTO) {

        ReservationSlot reservationSlot = reservationSlotRepository.findById(reservationDTO.getReservationSlotId())
                .orElseThrow(() -> new NotFoundException("Reservation slot not found"));


        if(reservationSlot.getAvailable()){
            ClientDto clientDto;
            try {
                ResponseEntity<ClientDto> response = userServiceRestTemplate.getForEntity("/user/" + reservationDTO.getUserId(), ClientDto.class);
                clientDto = response.getBody();
                if (clientDto == null) {
                    throw new NotFoundException(String.format("User %s not found", reservationDTO.getUserId()));
                }else System.err.println("User sa imenom:"+clientDto.getFirstName()+" dohvacen");
            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new NotFoundException(String.format("User %s not found", reservationDTO.getUserId()));
                } else {
                    throw new RuntimeException("Error accessing user service", e);
                }
            }

            System.err.println("br rezervacija pre: "+clientDto.getNumberOfReservation());
            int numOfReservations = clientDto.getNumberOfReservation() + 1;
            System.err.println("br rezervacija posle: "+numOfReservations);
            clientDto.setNumberOfReservation(numOfReservations);

            try {
                userServiceRestTemplate.put("/user/" + reservationDTO.getUserId(), clientDto);
            } catch (RestClientResponseException e) {
                throw new RuntimeException("Failed to update user data", e);
            }
            //TODO: POSLATI MEJL KLIJENTU I MENADZERU DA JE REZERVACIJA NAPRAVLJENA
            Reservation reservation = new Reservation(reservationSlot, reservationDTO.getUserId(), reservationDTO.getStatus(), reservationDTO.getCreatedOn(), reservationDTO.getEmail());
            NotificationDTO notification = new NotificationDTO(reservationDTO.getUserId(), reservationDTO.getEmail(), "RESERVATION_CREATED");
            notificationSender.sendNotification(notification);
            reservationRepository.save(reservation);
            reservationSlot.setAvailable(false);
            reservationSlotRepository.save(reservationSlot);
        }
        else System.err.println("RESERVATION SLOT NOT AVAILABLE");
    }

    @Override
    public ReservationDTO clientCancelReservation(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found"));
        if(reservation.getStatus()!=ReservationStatus.CANCELLED){
            ReservationDTO reservationDTO = new ReservationDTO(reservation.getId(),reservation.getReservationSlot().getId(),reservation.getUserId(),
                    ReservationStatus.CANCELLED,reservation.getCreatedOn(), reservation.getEmail());
            ClientDto clientDto;
            try {
                ResponseEntity<ClientDto> response = userServiceRestTemplate.getForEntity("/user/" + reservationDTO.getUserId(), ClientDto.class);
                clientDto = response.getBody();
                if (clientDto == null) {
                    throw new NotFoundException(String.format("User %s not found", reservationDTO.getUserId()));
                }else System.err.println("User sa imenom:"+clientDto.getFirstName()+" dohvacen");
            } catch (HttpStatusCodeException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new NotFoundException(String.format("User %s not found", reservationDTO.getUserId()));
                } else {
                    throw new RuntimeException("Error accessing user service", e);
                }
            }

            System.err.println("br rezervacija pre: "+clientDto.getNumberOfReservation());
            int numOfReservations = clientDto.getNumberOfReservation() - 1;
            System.err.println("br rezervacija posle: "+numOfReservations);
            clientDto.setNumberOfReservation(numOfReservations);

            try {
                userServiceRestTemplate.put("/user/" + reservationDTO.getUserId(), clientDto);
            } catch (RestClientResponseException e) {
                throw new RuntimeException("Failed to update user data", e);
            }
            ReservationSlot reservationSlot = reservationSlotRepository.findById(reservationDTO.getReservationSlotId())
                    .orElseThrow(() -> new NotFoundException("Reservation slot not found"));
            reservationSlot.setAvailable(true);
            reservationSlotRepository.save(reservationSlot);

            reservation.setStatus(ReservationStatus.CANCELLED);

            //TODO: POSLATI MEJL MENADZERU DA JE KLIJENT OTKAZAO REZERVACIJU
            reservationRepository.save(reservation);
            return reservationDTO;
        }
        else {
            System.err.println("RESERVATION ALREADY CANCELLED");
            return null;
        }
    }

    @Override
    public ReservationDTO managerCancelReservation(long id) {
        //TODO: POSLATI MEJL KLIJENTU DA JE MANAGER OTKAZAO REZERVACIJU
        return null;
    }
}
