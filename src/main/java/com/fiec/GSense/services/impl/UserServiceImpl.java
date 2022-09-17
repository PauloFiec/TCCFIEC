package com.fiec.GSense.services.impl;

import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getProfile(String userId) {
        return userRepository.findById(Integer.parseInt(userId)).orElseThrow();

        //.profileImage("http://maisexpressao.com.br/imagens/noticias/45537/640x480/airltonjpg.JPG")

    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email,
                new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8)).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User signUpUser(String name, String email, String password, String cpfOuCnpj, String phoneNumber) {
        return userRepository.save(
                User.builder()
                        .name(name)
                        .email(email)
                        .password(new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8))
                        .cpfOuCnpj(cpfOuCnpj)
                        .phoneNumber(phoneNumber)
                        .build()
        );
    }

    @Override
    public User updateUser(Integer userId, String name, String password, String phoneNumber) {
        User currentUser = userRepository.findById(userId).orElseThrow();
        currentUser.setName(name);
        currentUser.setPassword(password);
        currentUser.setPhoneNumber(phoneNumber);
        return userRepository.save(currentUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
