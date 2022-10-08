package com.fiec.GSense.models.dto;

import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDto {
    Integer deviceId;
    Integer deviceNumber;
    String ip;
    User user;
    DeviceInfo deviceInfo;

    public static DeviceDto convertToDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .deviceNumber(device.getDeviceNumber())
                .ip(device.getIp())
                .user(device.getUser())
                .deviceInfo(device.getDeviceInfo())
                .build();
    }
}


