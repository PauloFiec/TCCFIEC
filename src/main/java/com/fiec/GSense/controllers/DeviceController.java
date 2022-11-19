package com.fiec.GSense.controllers;

import com.fiec.GSense.Utils.CustomException;
import com.fiec.GSense.Utils.ResultCodesException;
import com.fiec.GSense.models.dto.CreateDeviceRequestDto;
import com.fiec.GSense.models.dto.DeviceDto;
import com.fiec.GSense.models.dto.UserDto;
import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.User;
import com.fiec.GSense.models.repositories.DeviceRepository;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    DeviceRepository deviceRepository;


    @GetMapping("/{deviceId}")
    public DeviceDto findDevice(@PathVariable("deviceId") String deviceId){
        return DeviceDto.convertToDeviceDto(deviceService.findDevice(deviceId));
    }

//    @GetMapping("/my")
//    public UserDto getProfile(Authentication authentication){
//        User user = (User) authentication.getPrincipal();
//        return UserDto.convertToUserDto(user);
//    }

    @GetMapping("/my")
    public List<DeviceDto> findAllDevice(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Device> listDevice = deviceRepository.findByAdminEmail(user.getEmail());
        return listDevice.stream().map(DeviceDto::convertToDeviceDto).collect(Collectors.toList());
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

//    @GetMapping("/my")
//    public DeviceDto getDevice(Authentication authentication){
//        User user = (User) authentication.getPrincipal();
//
//        return DeviceDto.convertToDeviceDto(device);
//    }


    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Integer deviceId) {
        deviceService.deleteDevice(deviceId);
    }

    @GetMapping("/test/{deviceNum}/{vazando}")
    public void testVazando(@PathVariable("deviceNum") Integer deviceNum, @PathVariable("vazando") Integer vazando){
        System.out.println(deviceNum + " : " + vazando);
    }

    @PostMapping("/dashboard/{deviceId}/{vazando}")
    public void dashboardVazando(@PathVariable("deviceId") Integer deviceId, @PathVariable("vazando") Integer vazando) {
        deviceService.testeVazando(deviceId, vazando);
    }

}
