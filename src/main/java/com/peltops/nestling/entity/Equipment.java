package com.peltops.nestling.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@RequiredArgsConstructor
public class Equipment {

    @Id
    @SequenceGenerator(name = "equipment_sequence", sequenceName = "equipment_sequence", allocationSize = 1)
    @GeneratedValue(generator = "equipment_sequence")
    private Long id;

    @NotBlank
    private String pei;

    private String supi;

    private String gpsi;

    private EquipmentStatus status;
}
