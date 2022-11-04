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
@Entity
@Table(name = "tb_device")
public class  Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deviceId;
    private Integer deviceNumber;
    private String ip;
    private Integer status;
    //power bi
    @ManyToOne
    private User user;
    @Embedded
    private DeviceInfo deviceInfo;
}
