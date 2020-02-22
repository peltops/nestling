package com.peltops.nestling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peltops.nestling.dto.CommonError;
import com.peltops.nestling.dto.ProblemDetails;
import com.peltops.nestling.service.EquipmentStatusService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EquipmentStatusController.class)
public class EquipmentStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentStatusService equipmentStatusService;

    @Value("${nestling.prefix}")
    private String prefix;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldReturnOK200ForValidImei() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                        .contentType("application/json")
                        .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOK200ForValidImeisv() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imeisv-0123456789012345"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOK200ForValidMac() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "mac-00-00-5E-00-53-00"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequest400ForMissingPei() throws Exception {
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn();
        System.err.println(result.getResponse().getContentAsString());
        ProblemDetails actual = objectMapper.readValue(result.getResponse().getContentAsString(), ProblemDetails.class);
        assertThat(actual.getCause()).isEqualTo(CommonError.MANDATORY_QUERY_PARAM_MISSING.toString());
        assertThat(actual.getInvalidParams()).isNotNull();
        assertThat(actual.getInvalidParams().size()).isEqualTo(1);
        assertThat(actual.getInvalidParams().get(0).getParam()).isEqualTo("query: pei");
        assertThat(actual.getInvalidParams().get(0).getReason()).isNotEmpty();
    }

    @Test
    public void shouldReturnBadRequest400ForInValidImei() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imei-01234567890123"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON));
    }

    @Test
    public void shouldReturnBadRequest400ForInValidImeisv() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imeisv-012345678901234"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequest400ForInValidMac() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "mac-012345678901234"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequest404ForMissingPei() throws Exception {
        mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
