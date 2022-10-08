package com.fiec.GSense.models.dto;

import com.fiec.GSense.models.entities.DeviceInfo;
import com.fiec.GSense.models.entities.User;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
public class CreateDeviceRequestDto {
    Integer deviceNumber;
    String ip;
    User userId;
    DeviceInfo deviceInfo;
}
