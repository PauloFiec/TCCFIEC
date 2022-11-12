package com.fiec.GSense.models.dto;

import com.fiec.GSense.enums.UserRoles;
import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String cpfOuCnpj;
    private UserRoles userRole;
}
