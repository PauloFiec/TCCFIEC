package com.fiec.GSense.models.entities;

import com.fiec.GSense.enums.StatusCompra;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private StatusCompra statusCompra;
    private String adminEmail;
    private String partnerId;
    //power bi
    @ManyToMany
    private List<User> users = new ArrayList<>();
    @Embedded
    private DeviceInfo deviceInfo;

}
