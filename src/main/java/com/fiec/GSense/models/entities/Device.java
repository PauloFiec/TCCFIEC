package com.fiec.GSense.models.entities;

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
    @ManyToOne
    private User user;
    private String nickname;
    private String cep;
    private String rua;
    private String bairro;
    private String numero;
    private String descricao;


}
