package com.fiec.GSense.controllers.models.dto;

import lombok.Data;

@Data
public class FirebaseResponseDto {
    String idToken;
    String email;
    String refreshToken;
    String expiresIn;
    String localId;
    String registered;
}
