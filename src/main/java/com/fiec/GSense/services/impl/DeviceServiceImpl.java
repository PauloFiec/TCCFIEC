package com.fiec.GSense.services.impl;

import com.fiec.GSense.models.dto.AdminRequestDto;
import com.fiec.GSense.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.DeviceRepository;
import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserRepository userRepository;

    @Override
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
                        .users(Collections.singletonList(currentUser))
                        .deviceInfo(DeviceInfo.builder()
                                .nickname(createDeviceRequestDto.getDeviceInfo().getNickname())
                                .cep(createDeviceRequestDto.getDeviceInfo().getCep())
                                .numero(createDeviceRequestDto.getDeviceInfo().getNumero())
                                .build())
                        .build()
        );
    }

    @Override
    public Device updateDevice(Integer deviceId, Integer deviceNumber, String ip, DeviceInfo deviceInfo) {
        Device currentDevice = deviceRepository.findById(deviceId).orElseThrow();
        currentDevice.setDeviceNumber(deviceNumber);
        currentDevice.setIp(ip);
        currentDevice.setDeviceInfo(DeviceInfo.builder()
                .nickname(currentDevice.getDeviceInfo().getNickname())
                .cep(currentDevice.getDeviceInfo().getCep())
                .numero(currentDevice.getDeviceInfo().getNumero())
                .build());
        return deviceRepository.save(currentDevice);
    }

    @Override
    public void deleteDevice(Integer deviceId) { deviceRepository.deleteById(deviceId);}

    @Override
    public void atualizaDevice(AdminRequestDto adminRequestDto) {
        Device device =
        deviceRepository.findByAdminEmailAndDeviceNumberAndPartnerId(
                adminRequestDto.getEmail(),
                adminRequestDto.getDeviceNumber(),
                adminRequestDto.getPartnerId()
        ).orElseThrow();
    device.setStatusCompra(adminRequestDto.getStausCompra());
    deviceRepository.save(device);
    }

}
