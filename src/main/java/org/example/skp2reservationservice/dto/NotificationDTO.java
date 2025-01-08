package org.example.skp2reservationservice.dto;
public class NotificationDTO {
    private Long userId;
    private String email;
    private String notificationType;

    public NotificationDTO() {}

    public NotificationDTO(Long userId, String email, String notificationType) {
        this.userId = userId;
        this.email = email;
        this.notificationType = notificationType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}