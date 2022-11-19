package com.fiec.GSense.models.repositories;

import com.fiec.GSense.models.entities.Device;
import com.fiec.GSense.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

    Optional<Device> findByAdminEmailAndDeviceNumberAndPartnerId(String adminEmail, Integer deviceNumber, String partnerId);

    Optional<Device> findByDeviceNumber(Integer deviceNumber);

    List<Device> findByAdminEmail(String adminEmail);
}

