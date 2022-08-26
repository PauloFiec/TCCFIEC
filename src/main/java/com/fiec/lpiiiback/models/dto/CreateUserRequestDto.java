package com.fiec.lpiiiback.models.dto;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    String name;
    String email;
    String password;
    String phoneNumber;
}
