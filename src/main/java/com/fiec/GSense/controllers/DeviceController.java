package com.fiec.GSense.controllers;

import com.fiec.GSense.Utils.CustomException;
import com.fiec.GSense.Utils.ResultCodesException;
import com.fiec.GSense.controllers.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.controllers.models.dto.DeviceDto;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("/{deviceId}")
    public DeviceDto findDevice(@PathVariable("deviceId") String deviceId){
        return DeviceDto.convertToDeviceDto(deviceService.findDevice(deviceId));
    }


    @PostMapping
    public DeviceDto addDevice(@RequestBody CreateDeviceRequestDto createDeviceRequestDto){
        try {
            return DeviceDto.convertToDeviceDto(deviceService.addDevice(
                    createDeviceRequestDto
            ));
        } catch (Exception ex){
            throw new CustomException(ResultCodesException.DEVICE_ALREADY_EXISTS);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){
//
//        User user = userService.login(
//                loginRequestDto.getEmail(),
//                loginRequestDto.getPassword()
//        );
//        if(user == null){
//            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
//        } else {
//            return ResponseEntity.status(Response.SC_OK).build();
//        }
//    }

    @PutMapping("/{deviceId}")
    public DeviceDto updateDevice(@RequestBody CreateDeviceRequestDto createDeviceRequestDto, @PathVariable("deviceId") Integer deviceId){
        return DeviceDto.convertToDeviceDto(deviceService.updateDevice(deviceId,
                createDeviceRequestDto.getDeviceNumber(),
                createDeviceRequestDto.getIp(),
                createDeviceRequestDto.getDeviceInfo()
        ));
    }

    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Integer deviceId) {
        deviceService.deleteDevice(deviceId);
    }



}
