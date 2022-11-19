package com.fiec.GSense.services.impl;

import com.fiec.GSense.enums.StatusCompra;
import com.fiec.GSense.models.dto.DeviceInfoDto;
import com.fiec.GSense.models.dto.SignUpDto;
import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.DeviceRepository;
import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.services.FirebaseService;
import com.fiec.GSense.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    DeviceRepository deviceRepository;

    @Override
    public User getProfile(String userId) {
        return userRepository.findById(Integer.parseInt(userId)).orElseThrow();

        //.profileImage("http://maisexpressao.com.br/imagens/noticias/45537/640x480/airltonjpg.JPG")

    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email,
                new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8)).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User signUpUser(SignUpDto signUpDto) {
        return firebaseService.signUp(signUpDto);

    }

    @Override
    public User updateUser(Integer userId, String name, String password, String phoneNumber) {
        User currentUser = userRepository.findById(userId).orElseThrow();
        currentUser.setName(name);
        currentUser.setPassword(password);
        currentUser.setPhoneNumber(phoneNumber);
        return userRepository.save(currentUser);
    }

    @Override
    public Integer buyDevice(User user, DeviceInfo deviceInfo){
        Device device = deviceRepository.save(Device.builder()
                        .users(Collections.singletonList(user))
                        .statusCompra(StatusCompra.Pending)
                        .adminEmail(user.getEmail())
                        .deviceInfo(deviceInfo)
                        .deviceNumber(new SecureRandom().nextInt(1000000000))
                .build()
        );
        return device.getDeviceId();
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
