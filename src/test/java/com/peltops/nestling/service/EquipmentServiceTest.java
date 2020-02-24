package com.peltops.nestling.service;

import com.peltops.nestling.entity.Equipment;
import com.peltops.nestling.entity.EquipmentStatus;
import com.peltops.nestling.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentStatusService equipmentStatusService;

    @Test
    void shouldReturnWhitelistedEquipment() {
        Equipment equipment = new Equipment();
        equipment.setPei("pei");
        equipment.setStatus(EquipmentStatus.WHITELISTED);
        when(equipmentRepository.findByPei("pei")).thenReturn(Optional.of(equipment));
        EquipmentStatus result = equipmentStatusService.getEquipmentStatus("pei", null, null);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(EquipmentStatus.WHITELISTED);
    }

    @Test
    void shouldThrowNotSuchElement() {
        when(equipmentRepository.findByPei("pei")).thenThrow(NoSuchElementException.class);
        assertThatThrownBy(() -> equipmentStatusService.getEquipmentStatus("pei", null, null)).isInstanceOf(NoSuchElementException.class);
    }

}
