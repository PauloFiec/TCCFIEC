package com.fiec.lpiiiback.models.dto;

import com.fiec.lpiiiback.models.entities.User;
import lombok.Data;

@Data
public class CreateDeviceRequestDto {
    Integer deviceNumber;
    Double ip;
    User userId;
    String nickname;
}
