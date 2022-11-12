package com.fiec.GSense.services.impl;

import com.fiec.GSense.models.dto.AuthRequestDto;
import com.fiec.GSense.models.dto.FirebaseRequestDto;
import com.fiec.GSense.models.dto.FirebaseResponseDto;
import com.fiec.GSense.models.dto.SignUpDto;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebaseServiceImpl implements FirebaseService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User signUp(SignUpDto signUpDto) {
        RestTemplate rt = new RestTemplate();
        FirebaseRequestDto firebaseRequestDto = FirebaseRequestDto.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .returnSecureToken(true)
                .build();
        HttpEntity<FirebaseRequestDto> entity = new HttpEntity<>(firebaseRequestDto);
        ResponseEntity<FirebaseResponseDto> res = rt.exchange(signUpUrl, HttpMethod.POST, entity, FirebaseResponseDto.class);
        FirebaseResponseDto firebaseResponseDto = res.getBody();
        User user =  User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .cpfOuCnpj(signUpDto.getCpfOuCnpj())
                .password(signUpDto.getPassword())
                .phoneNumber(signUpDto.getPhoneNumber())
                .userRole(signUpDto.getUserRole())
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDetails signIn(AuthRequestDto authRequestDto) {
        RestTemplate rt = new RestTemplate();
        FirebaseRequestDto firebaseRequestDto = FirebaseRequestDto.builder()
                .email(authRequestDto.getEmail())
                .password(authRequestDto.getPassword())
                .returnSecureToken(true)
                .build();
        HttpEntity<FirebaseRequestDto> entity = new HttpEntity<>(firebaseRequestDto);
        ResponseEntity<FirebaseResponseDto> res = rt.exchange(signInUrl, HttpMethod.POST, entity, FirebaseResponseDto.class);
        FirebaseResponseDto firebaseResponseDto = res.getBody();
        return userRepository.findByEmail(firebaseResponseDto.getEmail()).orElseThrow();
    }
}
