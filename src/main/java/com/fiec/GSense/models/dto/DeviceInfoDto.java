package com.fiec.GSense.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceInfoDto {
    private String nickname;
    private String cep;
    private String numero;
}
