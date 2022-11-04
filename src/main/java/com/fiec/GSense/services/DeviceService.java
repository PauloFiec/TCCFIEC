package com.fiec.GSense.services;

import com.fiec.GSense.controllers.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.controllers.models.entities.Device;
import com.fiec.GSense.controllers.models.entities.DeviceInfo;

public interface DeviceService {

    Device findDevice(String deviceId);

    Device addDevice(CreateDeviceRequestDto createDeviceRequestDto);

    Device updateDevice(Integer deviceId, Integer deviceNumber, String ip, DeviceInfo deviceInfo);

    void deleteDevice(Integer deviceId);
}
