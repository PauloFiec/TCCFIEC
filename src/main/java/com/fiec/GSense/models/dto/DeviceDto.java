package com.fiec.GSense.models.dto;

import com.fiec.GSense.enums.StatusCompra;
import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class DeviceDto {
    Integer deviceId;
    Integer deviceNumber;
    String ip;
    List<Integer> userIds;
    DeviceInfo deviceInfo;
    StatusCompra statusCompra;

    public static DeviceDto convertToDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .deviceNumber(device.getDeviceNumber())
                .ip(device.getIp())
                .userIds(device.getUsers().stream().map(User::getId).collect(Collectors.toList()))
                .deviceInfo(device.getDeviceInfo())
                .statusCompra(device.getStatusCompra())
                .build();
    }
}


