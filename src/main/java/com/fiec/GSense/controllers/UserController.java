package com.fiec.GSense.controllers;

import com.fiec.GSense.models.dto.CreateUserRequestDto;
import com.fiec.GSense.models.dto.LoginRequestDto;
import com.fiec.GSense.models.dto.UserDto;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.services.UserService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(){

        return userService.getAllUsers().stream().map(UserDto::convertToUserDto).collect(Collectors.toList());
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        User user = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        if(user == null){
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(Response.SC_OK).build();
        }
    }

    @GetMapping("/{userId}")
    public UserDto getProfile(@PathVariable("userId") String userId){

        return UserDto.convertToUserDto(userService.getProfile(userId));
    }

    @PutMapping("/{userId}")
    public UserDto editUser(@RequestBody CreateUserRequestDto createUserRequestDto, @PathVariable("userId") Integer userId){
        return UserDto.convertToUserDto(userService.updateUser(userId,
                createUserRequestDto.getName(),
                createUserRequestDto.getPassword(),
                createUserRequestDto.getPhoneNumber()
        ));

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId){
        userService.deleteUser(userId);

    }

//    @PostMapping(value="/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void createBulkOfUsers(@RequestParam("csvFile") MultipartFile multipartFile ) throws IOException {
//        BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
//        final int NAME=0, EMAIL=1, PASSWORD=2, PHONENUMBER=3, CPFOUCNPJ=4;
//        try (Reader reader = fileReader) {
//            try (CSVReader csvReader = new CSVReader(reader)) {
//                List<String[]> csvFields =  csvReader.readAll();
//                for(int i=1; i<csvFields.size(); i++){
//                    User newUser = User.builder()
//                            .email(csvFields.get(i)[EMAIL])
//                            .name(csvFields.get(i)[NAME])
//                            .password(csvFields.get(i)[PASSWORD])
//                            .phoneNumber(csvFields.get(i)[PHONENUMBER])
//                            .cpfOuCnpj(csvFields.get(i)[CPFOUCNPJ])
//                            .build();
//                    userService.signUpUser(newUser.getName(),
//                            newUser.getEmail(),
//                            newUser.getPassword(),
//                            newUser.getPhoneNumber(),
//                            newUser.getCpfOuCnpj());
//                }
//            } catch (CsvException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
