package com.fiec.lpiiiback.services.impl;

import com.fiec.lpiiiback.models.entities.UserJuridico;
import com.fiec.lpiiiback.models.repositories.UserJuridicoRepository;
import com.fiec.lpiiiback.services.UserJuridicoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class UserJuridicoServiceImpl implements UserJuridicoService {

    @Autowired
    UserJuridicoRepository userRepository;

    @Override
    public UserJuridico getProfile(String userId) {
        return userRepository.findById(Integer.parseInt(userId)).orElseThrow();
    }

    @Override
    public UserJuridico login(String email, String password) {
        return userRepository.findByEmailAndPassword(email,
                new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8)).orElse(null);
    }

    @Override
    public List<UserJuridico> getAllUsers() { return userRepository.findAll(); }

    @Override
    public UserJuridico signUpUser(String name, String nameFantasia, String razaoSocial, String cnpj, String email, String password, String phoneNumber,String fantasia) {
        return userRepository.save(
                UserJuridico.builder()
                        .name(name)
                        .email(email)
                        .nameFantasia(nameFantasia)
                        .razaoSocial(razaoSocial)
                        .cnpj(cnpj)
                        .phoneNumber(phoneNumber)
                        .password(new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8))
                        .build()
        );
    }

    @Override
    public UserJuridico updateUser(Integer userId, String name, String password, String phoneNumber) {
        UserJuridico currentUser = userRepository.findById(userId).orElseThrow();
        currentUser.setName(name);
        currentUser.setPassword(password);
        currentUser.setPhoneNumber(phoneNumber);
        return userRepository.save(currentUser);

    }

    @Override
    public void deleteUser(Integer userId) { userRepository.deleteById(userId); }
}
