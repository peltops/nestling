package com.peltops.nestling.entity;

import java.util.stream.Stream;

public enum EquipmentStatus {

    WHITELISTED(1),
    BLACKLISTED(2),
    GRAYLISTED(3);

    private Integer ordinal;

    private EquipmentStatus(final Integer ordinal) {
        this.ordinal = ordinal;
    }

    public Integer getOrdinal() {
        return this.ordinal;
    }

    public static EquipmentStatus of(Integer ordinal) {
        return Stream.of(EquipmentStatus.values())
                .filter(s -> s.getOrdinal().equals(ordinal))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
