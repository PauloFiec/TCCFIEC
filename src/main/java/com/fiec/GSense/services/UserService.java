package com.fiec.GSense.services;

import com.fiec.GSense.models.dto.DeviceInfoDto;
import com.fiec.GSense.models.dto.SignUpDto;
import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;

import java.util.List;

public interface UserService {
    User getProfile(String userId);
    User login(String email, String password);
    List<User> getAllUsers();
    User signUpUser(SignUpDto signUpDto);
    User updateUser(Integer userId, String name, String password, String phoneNumber);

    Integer buyDevice(User user, DeviceInfo deviceInfo);
    void deleteUser(Integer userId);

}
