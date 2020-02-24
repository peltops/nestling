package com.peltops.nestling.repository;

import com.peltops.nestling.entity.Equipment;
import com.peltops.nestling.entity.EquipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EquipmentRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EquipmentRepository equipmentRepository;

    private Equipment equipment;

    private String pei = "imei-012345678901234";

    @BeforeEach
    public void setup() {
        equipment = new Equipment();
        equipment.setPei(pei);
        equipment.setStatus(EquipmentStatus.WHITELISTED);
        equipment = equipmentRepository.save(equipment);
    }

    @Test
    public void checkComponents() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(equipmentRepository).isNotNull();
    }

    @Test
    public void shouldCreateAnEquipment() {
        assertThat(equipmentRepository.findByPei(pei)).isNotNull();
        assertThat(equipment.getPei()).isEqualTo(pei);
        assertThat(equipment.getStatus()).isEqualTo(EquipmentStatus.WHITELISTED);
    }

    @Test
    public void shouldFindAnEquipment() {
        Optional<Equipment> foundEquipment = equipmentRepository.findByPei(pei);
        assertThat(foundEquipment.isPresent()).isTrue();
        assertThat(equipmentRepository.findByPei(pei)).isNotNull();
        assertThat(foundEquipment.get().getPei()).isEqualTo(pei);
        assertThat(foundEquipment.get().getStatus()).isEqualTo(EquipmentStatus.WHITELISTED);
    }

    @Test
    public void shouldNotFindAnEquipment() {
        String noSuchThing = "no-such-thing";
        Optional<Equipment> foundEquipment = equipmentRepository.findByPei(noSuchThing);
        assertThat(foundEquipment.isPresent()).isFalse();
        assertThat(equipmentRepository.findByPei(noSuchThing)).isNotNull();
        assertThatThrownBy(() -> equipmentRepository.findByPei(noSuchThing).get()).isInstanceOf(NoSuchElementException.class);
    }

}
