package com.fiec.lpiiiback.models.repositories;

import com.fiec.lpiiiback.models.entities.Device;
import com.fiec.lpiiiback.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Optional<Device> findDevice(Integer deviceId);
}

