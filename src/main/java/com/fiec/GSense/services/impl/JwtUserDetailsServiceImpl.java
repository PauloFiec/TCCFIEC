package com.fiec.GSense.services.impl;

import com.fiec.GSense.controllers.models.entities.User;
import com.fiec.GSense.controllers.models.repositories.UserRepository;
import com.fiec.GSense.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User loadByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
}
