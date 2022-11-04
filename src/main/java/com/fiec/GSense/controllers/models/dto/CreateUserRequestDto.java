package com.fiec.GSense.controllers.models.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    String name;
    String email;
    String password;
    String phoneNumber;
    String cpf;
}
