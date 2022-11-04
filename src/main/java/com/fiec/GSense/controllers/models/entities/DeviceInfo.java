package com.fiec.GSense.controllers.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeviceInfo {

    private String nickname;
    private String cep;
    private String numero;
}

/*
id
Number
Status if status == 1: print("OK") else: print("Vazamento")
 */