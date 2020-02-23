package com.peltops.nestling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peltops.nestling.dto.CommonError;
import com.peltops.nestling.dto.EirResponseData;
import com.peltops.nestling.dto.InvalidParam;
import com.peltops.nestling.dto.ProblemDetails;
import com.peltops.nestling.entity.EquipmentStatus;
import com.peltops.nestling.service.EquipmentStatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static com.peltops.nestling.util.ResponseBodyMatchers.responseBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
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

    @Autowired
    private ObjectMapper objectMapper;

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
        InvalidParam invalidParam = new InvalidParam();
        invalidParam.setParam("query: pei");
        invalidParam.setReason("Required String parameter 'pei' is not present");
        List<InvalidParam> invalidParamList = new ArrayList<InvalidParam>();
        invalidParamList.add(invalidParam);
        ProblemDetails expected = new ProblemDetails();
        expected.setCause(CommonError.MANDATORY_QUERY_PARAM_MISSING.toString());
        expected.setInvalidParams(invalidParamList);
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(responseBody().containsObjectAsJson(expected, ProblemDetails.class))
                .andReturn();
        ProblemDetails actual = objectMapper.readValue(result.getResponse().getContentAsString(), ProblemDetails.class);
        assertThat(actual.getCause()).isEqualTo(CommonError.MANDATORY_QUERY_PARAM_MISSING.toString());
        assertThat(actual.getInvalidParams()).isNotNull();
        assertThat(actual.getInvalidParams().size()).isEqualTo(1);
        assertThat(actual.getInvalidParams().get(0).getParam()).isEqualTo("query: pei");
        assertThat(actual.getInvalidParams().get(0).getReason()).isNotEmpty();
    }

    @Test
    public void shouldReturnWhitelistedWithValidPeiNullSupiNullGpsi() throws Exception {
        when(equipmentStatusService.getEquipmentStatus(any(String.class), isNull(), isNull()))
                .thenReturn(EquipmentStatus.WHITELISTED);
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnWhitelistedWithValidPeiValidSupiNullGpsi() throws Exception {
        EirResponseData expectedData = new EirResponseData();
        expectedData.setStatus(EquipmentStatus.WHITELISTED);
        when(equipmentStatusService.getEquipmentStatus(any(String.class), isNull(), isNull()))
                .thenReturn(EquipmentStatus.WHITELISTED);
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk())
                .andExpect(responseBody().containsObjectAsJson(expectedData, EirResponseData.class))
                .andReturn();
    }

    @Test
    public void shouldReturnBlacklistedWithValidPeiValidSupiNullGpsi() throws Exception {
        EirResponseData expectedData = new EirResponseData();
        expectedData.setStatus(EquipmentStatus.BLACKLISTED);
        when(equipmentStatusService.getEquipmentStatus(any(String.class), isNull(), isNull()))
                .thenReturn(EquipmentStatus.BLACKLISTED);
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk())
                .andExpect(responseBody().containsObjectAsJson(expectedData, EirResponseData.class))
                .andReturn();
    }

    @Test
    public void shouldReturnGraylistedWithValidPeiValidSupiNullGpsi() throws Exception {
        EirResponseData expectedData = new EirResponseData();
        expectedData.setStatus(EquipmentStatus.GRAYLISTED);
        when(equipmentStatusService.getEquipmentStatus(any(String.class), isNull(), isNull()))
                .thenReturn(EquipmentStatus.GRAYLISTED);
        MvcResult result = mockMvc.perform(get(prefix + "/n5g-eir-eic/v1")
                .contentType("application/json")
                .param("pei", "imei-012345678901234"))
                .andExpect(status().isOk())
                .andExpect(responseBody().containsObjectAsJson(expectedData, EirResponseData.class))
                .andReturn();
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
