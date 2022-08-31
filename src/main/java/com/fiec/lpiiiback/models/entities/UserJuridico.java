package com.fiec.lpiiiback.models.entities;

import com.sun.istack.NotNull;
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
@Table(name = "tb_userJuridico")
public class UserJuridico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String name;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String nameFantasia;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String razaoSocial;

    @Column(unique = true)
    @NotBlank(message = "Essa campo é obrigatório.")
    private String email;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String phoneNumber;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String password;

    @NotBlank(message = "Essa campo é obrigatório.")
    private String cnpj;

}
