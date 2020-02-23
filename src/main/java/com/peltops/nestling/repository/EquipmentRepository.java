package com.peltops.nestling.repository;

import com.peltops.nestling.entity.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

    public Optional<Equipment> findByPei(String pei);
}
