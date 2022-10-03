package com.fiec.GSense.services;

import com.fiec.GSense.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.models.entities.Device;

public interface DeviceService {

    Device findDevice(String deviceId);

    Device addDevice(CreateDeviceRequestDto createDeviceRequestDto);

    Device updateDevice(Integer deviceId, Integer deviceNumber, String ip, String nickname, String cep, String rua, String bairro, String numero, String descricao);

    void deleteDevice(Integer deviceId);
}
