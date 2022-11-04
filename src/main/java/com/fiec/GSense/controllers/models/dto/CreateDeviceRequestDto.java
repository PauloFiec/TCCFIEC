package com.fiec.GSense.controllers.models.dto;

import com.fiec.GSense.controllers.models.entities.DeviceInfo;
import com.fiec.GSense.controllers.models.entities.User;
import lombok.Data;

@Data
public class CreateDeviceRequestDto {
    Integer deviceNumber;
    String ip;
    User userId;
    DeviceInfo deviceInfo;
}
