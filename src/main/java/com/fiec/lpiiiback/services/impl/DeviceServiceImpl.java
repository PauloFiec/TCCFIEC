package com.fiec.lpiiiback.services.impl;

import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import com.fiec.lpiiiback.models.dto.CreateDeviceRequestDto;
import com.fiec.lpiiiback.models.entities.Device;
import com.fiec.lpiiiback.models.entities.User;
import com.fiec.lpiiiback.models.repositories.DeviceRepository;
import com.fiec.lpiiiback.models.repositories.UserRepository;
import com.fiec.lpiiiback.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserRepository userRepository;

    public Device findDevice(String deviceId) {
        return deviceRepository.findById(Integer.parseInt(deviceId)).orElseThrow();
    }

    @Override
    public Device addDevice(CreateDeviceRequestDto createDeviceRequestDto) {
        User currentUser = userRepository.findById(createDeviceRequestDto.getUserId().getId()).orElseThrow();
        return deviceRepository.save(
                Device.builder()
                        .deviceNumber(createDeviceRequestDto.getDeviceNumber())
                        .ip(createDeviceRequestDto.getIp())
                        .user(currentUser)
                        .nickname(createDeviceRequestDto.getNickname())
                        .build()
        );
    }

    @Override
    public Device updateDevice(Integer deviceId, Integer deviceNumber, Double ip, String nickname) {
        Device currentDevice = deviceRepository.findById(deviceId).orElseThrow();
        currentDevice.setDeviceNumber(deviceNumber);
        currentDevice.setIp(ip);
        currentDevice.setNickname(nickname);
        return deviceRepository.save(currentDevice);
    }

    @Override
    public void deleteDevice(Integer deviceId) { deviceRepository.deleteById(deviceId);}
}
