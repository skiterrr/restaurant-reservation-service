package org.example.skp2reservationservice.client.users.dto;


public class TokenResponseDto {
    private String token;
    public TokenResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
