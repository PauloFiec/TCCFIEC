package com.fiec.GSense.models.dto;

import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.User;
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
    String cep;
    String rua;
    String bairro;
    String numero;
    String descricao;

    public static DeviceDto convertToDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .deviceNumber(device.getDeviceNumber())
                .ip(device.getIp())
                .user(device.getUser())
                .nickname(device.getNickname())
                .cep(device.getCep())
                .bairro(device.getBairro())
                .numero(device.getNumero())
                .descricao(device.getDescricao())
                .build();
    }
}


