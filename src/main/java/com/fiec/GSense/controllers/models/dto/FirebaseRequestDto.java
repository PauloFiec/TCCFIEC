package com.fiec.GSense.controllers.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FirebaseRequestDto {
    String email;
    String password;
    Boolean returnSecureToken;
}
