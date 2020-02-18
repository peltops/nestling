package com.peltops.nestling.repository;

import com.peltops.nestling.entity.Equipment;
import com.peltops.nestling.entity.EquipmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void checkComponents() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(equipmentRepository).isNotNull();
    }

    @Test
    public void shouldCreateAnEquipment() {
        Equipment equipment = new Equipment();
        equipment.setPei("imei-012345678901234");
        equipment.setStatus(EquipmentStatus.WHITELISTED);

        equipment = equipmentRepository.save(equipment);
        System.err.println(equipment);

    }

}
