package com.fiec.GSense.models.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    String name;
    String email;
    String password;
    String cpf;
    String phoneNumber;
}
