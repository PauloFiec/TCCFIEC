package com.fiec.GSense.models.dto;

import com.fiec.GSense.enums.StatusCompra;
import lombok.Data;

@Data
public class AdminRequestDto {
    private String partnerId;
    private Integer deviceNumber;
    private String email;
    private StatusCompra stausCompra;

}
