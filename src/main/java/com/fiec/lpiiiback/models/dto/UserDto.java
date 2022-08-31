package com.fiec.lpiiiback.models.dto;

import com.fiec.lpiiiback.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    Integer id;
    String email;
    String name;
    String phoneNumber;
    String cpf;

    public static UserDto convertToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .cpf(user.getCpf())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
