package com.fiec.lpiiiback.models.dto;

import com.fiec.lpiiiback.models.entities.UserJuridico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserJuridicoDto {
    Integer id;
    String email;
    String name;
    String phoneNumber;
    String razaoSocial;
    String nameFantasia;
    String cpnj;


    public static UserJuridicoDto convertTo(UserJuridico user){
        return UserJuridicoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .razaoSocial(user.getRazaoSocial())
                .nameFantasia(user.getNameFantasia())
                .cpnj(user.getCnpj())
                .build();
    }
}
