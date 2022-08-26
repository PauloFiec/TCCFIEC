package com.fiec.lpiiiback.models.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer deviceId;
    private Integer deviceNumber;
}
