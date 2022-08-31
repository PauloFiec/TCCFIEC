package com.fiec.lpiiiback.services;

import com.fiec.lpiiiback.models.entities.User;
import com.fiec.lpiiiback.models.entities.UserJuridico;

import java.io.File;
import java.util.List;

/**
 * Representa os casos de uso
 */
public interface UserService {
    User getProfile(String userId);
    User login(String email, String password);
    List<User> getAllUsers();
    User signUpUser(String name, String email, String password, String phoneNumber, String cpf);

    User updateUser(Integer userId, String name, String password, String phoneNumber);

    void deleteUser(Integer userId);

}
