package com.fiec.GSense.services;


import com.fiec.GSense.models.dto.AuthRequestDto;

public interface FirebaseService {
    String signUpUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";
    String signInUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";

    void signUp(AuthRequestDto authRequestDto);
    String signIn(AuthRequestDto authRequestDto);
}
