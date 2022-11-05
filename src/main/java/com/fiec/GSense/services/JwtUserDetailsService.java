package com.fiec.GSense.services;


import com.fiec.GSense.models.entities.User;

public interface JwtUserDetailsService {
    User loadByEmail(String email);
}
