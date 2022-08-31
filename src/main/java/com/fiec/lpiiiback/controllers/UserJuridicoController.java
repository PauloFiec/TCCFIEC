package com.fiec.lpiiiback.controllers;


import com.fiec.lpiiiback.models.dto.CreateUserJuridicoDto;
import com.fiec.lpiiiback.models.dto.LoginRequestDto;
import com.fiec.lpiiiback.models.dto.UserJuridicoDto;
import com.fiec.lpiiiback.models.entities.UserJuridico;
import com.fiec.lpiiiback.services.UserJuridicoService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/usersJuridico")
public class UserJuridicoController {

    @Autowired
    UserJuridicoService userService;

    @PostMapping
    public UserJuridicoDto signUpUserJuridico(@RequestBody CreateUserJuridicoDto createUserRequestDto) {
        return UserJuridicoDto.convertTo(userService.signUpUser(
                createUserRequestDto.getName(),
                createUserRequestDto.getNameFantasia(),
                createUserRequestDto.getRazaoSocial(),
                createUserRequestDto.getCnpj(),
                createUserRequestDto.getEmail(),
                createUserRequestDto.getPhoneNumber(),
                createUserRequestDto.getPassword(),
                createUserRequestDto.getNameFantasia()));
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

        UserJuridico user = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        if(user == null){
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(Response.SC_OK).build();
        }
    }

    @GetMapping("/{userJuridicoId}")
    public UserJuridicoDto getProfile(@PathVariable("userJuridicoId") String userJuridicoId){
        return  UserJuridicoDto.convertTo(userService.getProfile(userJuridicoId));
    }

    @PutMapping("/{userJuridicoId}")
    public UserJuridicoDto editUser(@RequestBody CreateUserJuridicoDto createUserJuridicoDto, @PathVariable("userJuridicoId") Integer userJuridicoId){
        return UserJuridicoDto.convertTo(userService.updateUser(userJuridicoId,
                createUserJuridicoDto.getName(),
                createUserJuridicoDto.getPhoneNumber(),
                createUserJuridicoDto.getPassword()
                ));
    }

    @DeleteMapping("/{userJuridicoId}")
    public void deleteUser(@PathVariable("userJuridicoId") Integer userJuridicoId) {
        userService.deleteUser(userJuridicoId);
    }

    @PostMapping(value="/csvJ", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createBulkOfUsers(@RequestParam("csvFile") MultipartFile multipartFile ) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
        final int NAME=0, EMAIL=1, PASSWORD=2, PHONENUMBER=3, CNPJ=4, RAZAOSOCIAL=5, NAMEFANTASIA=6;
        try (Reader reader = fileReader) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> csvFields =  csvReader.readAll();
                for(int i=1; i<csvFields.size(); i++){
                    UserJuridico newUserJuridico = UserJuridico.builder()
                            .email(csvFields.get(i)[EMAIL])
                            .name(csvFields.get(i)[NAME])
                            .password(csvFields.get(i)[PASSWORD])
                            .phoneNumber(csvFields.get(i)[PHONENUMBER])
                            .cnpj(csvFields.get(i)[CNPJ])
                            .razaoSocial(csvFields.get(i)[RAZAOSOCIAL])
                            .nameFantasia(csvFields.get(i)[NAMEFANTASIA])
                            .build();
                    userService.signUpUser(newUserJuridico.getName(),
                            newUserJuridico.getEmail(),
                            newUserJuridico.getCnpj(),
                            newUserJuridico.getPassword(),
                            newUserJuridico.getPhoneNumber(),
                            newUserJuridico.getCnpj(),
                            newUserJuridico.getRazaoSocial(),
                            newUserJuridico.getNameFantasia());
                }
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}