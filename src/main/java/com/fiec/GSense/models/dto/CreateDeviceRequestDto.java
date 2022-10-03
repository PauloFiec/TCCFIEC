package com.fiec.GSense.models.dto;

import com.fiec.GSense.models.entities.User;
import lombok.Data;

@Data
public class CreateDeviceRequestDto {
    Integer deviceNumber;
    String ip;
    User userId;
    String nickname;
    String cep;
    String rua;
    String bairro;
    String numero;
    String descricao;
}
