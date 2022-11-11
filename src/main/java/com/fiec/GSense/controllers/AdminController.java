package com.fiec.GSense.controllers;

import com.fiec.GSense.models.dto.AdminRequestDto;
import com.fiec.GSense.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    DeviceService deviceService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public void atualiza(@RequestBody AdminRequestDto adminRequestDto) {
        deviceService.atualizaDevice(adminRequestDto);
    }
}
