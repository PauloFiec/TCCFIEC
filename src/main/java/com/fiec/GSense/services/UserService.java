package com.fiec.GSense.services;

import com.fiec.GSense.models.entities.User;

import java.util.List;

public interface UserService {
    User getProfile(String userId);
    User login(String email, String password);
    List<User> getAllUsers();
    User signUpUser(String name, String email, String password, String phoneNumber, String cpfOuCnpj, Boolean isJuridico);
    User updateUser(Integer userId, String name, String password, String phoneNumber);

    void deleteUser(Integer userId);

}
