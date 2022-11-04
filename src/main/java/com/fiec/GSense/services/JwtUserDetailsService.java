package com.fiec.GSense.services;


import com.fiec.GSense.controllers.models.entities.User;

public interface JwtUserDetailsService {
    User loadByEmail(String email);
}
