package com.fiec.lpiiiback.services;

import com.fiec.lpiiiback.models.dto.CreateDeviceRequestDto;
import com.fiec.lpiiiback.models.entities.Device;

public interface DeviceService {

    Device findDevice(String deviceId);

    Device addDevice(CreateDeviceRequestDto createDeviceRequestDto);

    Device updateDevice(Integer deviceId, Integer deviceNumber, Double ip, String nickname);

    void deleteDevice(Integer deviceId);
}
