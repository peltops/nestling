package com.peltops.nestling.service;

import com.peltops.nestling.entity.Equipment;
import com.peltops.nestling.entity.EquipmentStatus;
import com.peltops.nestling.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentStatusService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentStatus getEquipmentStatus(String pei, String supi, String gpsi) {
        Optional<Equipment> equipment = equipmentRepository.findByPei(pei);
        return equipment.get().getStatus();
    }
}
