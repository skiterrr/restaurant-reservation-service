package org.example.skp2reservationservice.service;

import org.example.skp2reservationservice.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendNotification(NotificationDTO notification) {
        try {
            String jsonNotification = objectMapper.writeValueAsString(notification);
            jmsTemplate.convertAndSend("notificationQueue", jsonNotification);
            System.out.println("Poslata notifikacija tipa: " + notification.getNotificationType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
