package com.fiec.GSense.controllers.models.repositories;

import com.fiec.GSense.controllers.models.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

}

