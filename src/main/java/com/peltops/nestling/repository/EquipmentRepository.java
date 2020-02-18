package com.peltops.nestling.repository;

import com.peltops.nestling.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    public Equipment findByPei(String pei);
}
