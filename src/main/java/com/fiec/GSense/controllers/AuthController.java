package com.fiec.GSense.controllers;

import com.fiec.GSense.Utils.CustomException;
import com.fiec.GSense.Utils.JwtTokenUtil;
import com.fiec.GSense.models.dto.*;
import com.fiec.GSense.Utils.ResultCodesException;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.services.FirebaseService;
import com.fiec.GSense.services.JwtUserDetailsService;
import com.fiec.GSense.services.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    UserService userService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public UserDto signUpUser(@RequestBody SignUpDto signUpDto) {

        try {
            UserDto userDto =  UserDto.convertToUserDto(userService.signUpUser(signUpDto));
            return userDto;
        } catch (Exception ex) {
            throw new CustomException(ResultCodesException.USER_ALREADY_EXISTS);
        }
    }

    @PostMapping("/signIn")
    public LoginResponseDto signIn(@RequestBody AuthRequestDto authRequestDto){
        UserDetails user = firebaseService.signIn(authRequestDto);
        String token = jwtTokenUtil.generateToken(user);
        return LoginResponseDto.builder()
                .token(token)
                .build();
    }


    @PostMapping("/loginWithGoogle")
    public LoginResponseDto signInWithGoogle(@RequestBody LoginGoogleRequestDto loginGoogleRequestDto) throws GeneralSecurityException, IOException, HttpException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken googleTokenResponse = verifier.verify(loginGoogleRequestDto.getTokenId());

        if(googleTokenResponse != null){
            GoogleIdToken.Payload payload = googleTokenResponse.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            UserDetails userDetails = jwtUserDetailsService.loadByEmail(email);

            String token = jwtTokenUtil.generateToken(userDetails);
            return LoginResponseDto.builder()
                    .token(token)
                    .build();
        }
        throw new HttpException();
    }
}
