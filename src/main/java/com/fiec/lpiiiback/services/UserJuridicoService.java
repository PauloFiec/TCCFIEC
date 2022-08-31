package com.fiec.lpiiiback.services;

import com.fiec.lpiiiback.models.entities.UserJuridico;

import java.util.List;

public interface UserJuridicoService {
    UserJuridico getProfile(String userId);

    UserJuridico login(String email, String password);

    List<UserJuridico> getAllUsers();

    UserJuridico signUpUser(String name, String email, String password, String phoneNumber, String cnpj, String razaoSocial, String nameFantasia, String fantasia);

    UserJuridico updateUser(Integer userId, String name, String password, String phoneNumber);

    void deleteUser(Integer userId);

}
