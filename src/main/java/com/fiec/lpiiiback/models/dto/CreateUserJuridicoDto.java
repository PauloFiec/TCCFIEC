package com.fiec.lpiiiback.models.dto;

import lombok.Data;

@Data
public class CreateUserJuridicoDto {
    String name;
    String nameFantasia;
    String razaoSocial;
    String cnpj;
    String email;
    String phoneNumber;
    String password;

}
