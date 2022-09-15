package com.fiec.GSense.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Essa campo é obrigatório.")

    private String name;

    @Column(unique = true)
    @NotBlank(message = "Essa campo é obrigatório.")
    private String email;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String phoneNumber;

    private String password;

    private String cpfOuCnpj;
}
