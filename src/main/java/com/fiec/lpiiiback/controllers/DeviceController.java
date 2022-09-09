package com.fiec.lpiiiback.controllers;

import com.fiec.lpiiiback.models.dto.*;
import com.fiec.lpiiiback.models.entities.Device;
import com.fiec.lpiiiback.services.DeviceService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @PostMapping
    public DeviceDto addDevice(@RequestBody CreateDeviceRequestDto createDeviceRequestDto){

        return DeviceDto.convertToDeviceDto(deviceService.addDevice(
                createDeviceRequestDto.getDeviceNumber(),
                createDeviceRequestDto.getIp(),
                createDeviceRequestDto.getUser(),
                createDeviceRequestDto.getNickname()
        ));
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

    @GetMapping("/{deviceId}")
    public DeviceDto getProfile(@PathVariable("deviceId") String deviceId){

        return DeviceDto.convertToDeviceDto(deviceService.findDevice(deviceId));
    }

    @PutMapping("/{deviceId}")
    public DeviceDto updateDevice(@RequestBody CreateDeviceRequestDto createDeviceRequestDto, @PathVariable("deviceId") Integer deviceId){
       return DeviceDto.convertToDeviceDto(deviceService.updateDevice(deviceId,
               createDeviceRequestDto.getDeviceNumber(),
               createDeviceRequestDto.getIp(),
               createDeviceRequestDto.getNickname()
       ));
    }

    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Integer deviceId) {
        deviceService.deleteDevice(deviceId);
    }

    @PostMapping(value="/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createBulkOfDevice(@RequestParam("csvFile") MultipartFile multipartFile ) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
        final int DEVICENUMBER=0, IP=1, NICKNAME=2, USER=3;
        try (Reader reader = fileReader) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> csvFields =  csvReader.readAll();
                for(int i=1; i<csvFields.size(); i++){
                    Device newDevice = Device.builder()
                            .deviceNumber(Integer.valueOf(csvFields.get(i)[DEVICENUMBER]))
                            .ip(Double.valueOf(csvFields.get(i)[IP]))
                            .nickname(csvFields.get(i)[NICKNAME])
                            .user(csvFields.get(i)[USER])
                            .build();
                    deviceService.addDevice(newDevice.getDeviceNumber(),
                            newDevice.getIp(),
                            newDevice.getUser(),
                            newDevice.getNickname();
                }
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
