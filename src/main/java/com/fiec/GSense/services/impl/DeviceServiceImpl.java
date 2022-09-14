package com.fiec.GSense.services.impl;

import com.fiec.GSense.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.DeviceRepository;
import com.fiec.GSense.models.repositories.UserRepository;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                        .user(currentUser)
                        .nickname(createDeviceRequestDto.getNickname())
                        .cep(createDeviceRequestDto.getCep())
                        .rua(createDeviceRequestDto.getRua())
                        .bairro(createDeviceRequestDto.getBairro())
                        .numero(createDeviceRequestDto.getNumero())
                        .descricao(createDeviceRequestDto.getDescricao())
                        .build()
        );
    }

    @Override
    public Device updateDevice(Integer deviceId, Integer deviceNumber, Double ip, String nickname, String cep, String rua, String bairro, String numero, String descricao) {
        Device currentDevice = deviceRepository.findById(deviceId).orElseThrow();
        currentDevice.setDeviceNumber(deviceNumber);
        currentDevice.setIp(ip);
        currentDevice.setNickname(nickname);
        currentDevice.getCep();
        currentDevice.getRua();
        currentDevice.getBairro();
        currentDevice.getNumero();
        currentDevice.getDescricao();
        return deviceRepository.save(currentDevice);
    }

    @Override
    public void deleteDevice(Integer deviceId) { deviceRepository.deleteById(deviceId);}
}
