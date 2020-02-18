package com.peltops.nestling.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EquipmentStatusConverter implements AttributeConverter<EquipmentStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EquipmentStatus equipmentStatus) {
        if (equipmentStatus == null) {
            return null;
        }
        return equipmentStatus.getOrdinal();
    }

    @Override
    public EquipmentStatus convertToEntityAttribute(Integer ordinal) {
        if (ordinal == null) {
            return null;
        }
        return EquipmentStatus.of(ordinal);
    }
}
