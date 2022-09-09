package com.fiec.lpiiiback.models.dto;

import com.fiec.lpiiiback.models.entities.Device;
import com.fiec.lpiiiback.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDto {
    Integer deviceId;
    Integer deviceNumber;
    Double ip;
    User user;
    String nickname;

    public static DeviceDto convertToDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .deviceNumber(device.getDeviceNumber())
                .ip(device.getIp())
                .user(device.getUser())
                .nickname(device.getNickname())
                .build();
    }
}


