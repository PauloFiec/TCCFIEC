package com.fiec.GSense.services;


import com.fiec.GSense.models.dto.AuthRequestDto;
import com.fiec.GSense.models.dto.SignUpDto;
import com.fiec.GSense.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface FirebaseService {
    String signUpUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";
    String signInUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";

    User signUp(SignUpDto signUpDto);
    UserDetails signIn(AuthRequestDto authRequestDto);
}
