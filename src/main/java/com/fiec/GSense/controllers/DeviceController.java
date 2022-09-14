package com.fiec.GSense.controllers;

import com.fiec.GSense.Utils.CustomException;
import com.fiec.GSense.Utils.ResultCodesException;
import com.fiec.GSense.models.dto.*;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

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

    @GetMapping("/{deviceId}")
    public DeviceDto findDevice(@PathVariable("deviceId") String deviceId){
        return DeviceDto.convertToDeviceDto(deviceService.findDevice(deviceId));
    }

    @PutMapping("/{deviceId}")
    public DeviceDto updateDevice(@RequestBody CreateDeviceRequestDto createDeviceRequestDto, @PathVariable("deviceId") Integer deviceId){
       return DeviceDto.convertToDeviceDto(deviceService.updateDevice(deviceId,
               createDeviceRequestDto.getDeviceNumber(),
               createDeviceRequestDto.getIp(),
               createDeviceRequestDto.getNickname(),
               createDeviceRequestDto.getCep(),
               createDeviceRequestDto.getRua(),
               createDeviceRequestDto.getBairro(),
               createDeviceRequestDto.getNumero(),
               createDeviceRequestDto.getDescricao()
       ));
    }

    @DeleteMapping("/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Integer deviceId) {
        deviceService.deleteDevice(deviceId);
    }

//    @PostMapping(value="/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public void createBulkOfDevice(@RequestParam("csvFile") MultipartFile multipartFile ) throws IOException {
//        BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream(), "UTF-8"));
//        final int DEVICENUMBER=0, IP=1, NICKNAME=2, USER=3;
//        try (Reader reader = fileReader) {
//            try (CSVReader csvReader = new CSVReader(reader)) {
//                List<String[]> csvFields =  csvReader.readAll();
//                for(int i=1; i<csvFields.size(); i++){
//                    Device newDevice = Device.builder()
//                            .deviceNumber(Integer.valueOf(csvFields.get(i)[DEVICENUMBER]))
//                            .ip(Double.valueOf(csvFields.get(i)[IP]))
//                            .nickname(csvFields.get(i)[NICKNAME])
//                            .user(csvFields.get(i)[USER])
//                            .build();
//                    deviceService.addDevice(newDevice.getDeviceNumber(),
//                            newDevice.getIp(),
//                            newDevice.getUser(),
//                            newDevice.getNickname();
//                }
//            } catch (CsvException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
