package com.peltops.nestling.controller;

import com.peltops.nestling.service.EquipmentStatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EquipmentStatusController.class)
public class EquipmentStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentStatusService equipmentStatusService;

    @Value("${nestling.prefix}")
    private String prefix;

    @Test
    public void shouldReturnOKForValidPei() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                        .contentType("application/json")
                        .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk());
    }
}
