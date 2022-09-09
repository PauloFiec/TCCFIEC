package com.fiec.lpiiiback.services;

import com.fiec.lpiiiback.models.entities.Device;
import com.fiec.lpiiiback.models.entities.User;

public interface DeviceService {

    Device findDevice(String deviceId);

    Device addDevice(Integer deviceNumber, Double ip, User user, String nickname);

    Device updateDevice(Integer deviceId, Integer deviceNumber, Double ip, String nickname);

    void deleteDevice(Integer deviceId);
}
