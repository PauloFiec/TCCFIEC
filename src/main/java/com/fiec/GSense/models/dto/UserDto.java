package com.fiec.GSense.models.dto;

import com.fiec.GSense.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    Integer id;
    String email;
    String name;
    String getCpfOuCnpj;
    String phoneNumber;

    public static UserDto convertToUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .getCpfOuCnpj(user.getCpfOuCnpj())
                .build();
    }
}
