package com.peltops.nestling.service;

import com.peltops.nestling.entity.EquipmentStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipmentStatusService {

    public Optional<EquipmentStatus> getEquipmentStatus(String pei, String supi, String gpsi) {
        Optional<EquipmentStatus> status = null;
        return status;
    }
}
