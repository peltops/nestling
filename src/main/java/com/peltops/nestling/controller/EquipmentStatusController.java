package com.peltops.nestling.controller;

import com.peltops.nestling.dto.EirResponseData;
import com.peltops.nestling.service.EquipmentStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@Validated
public class EquipmentStatusController {

    private final EquipmentStatusService equipmentStatusService;

    @GetMapping("${nestling.prefix}/n5g-eir-eic/v1")
    public ResponseEntity<EirResponseData> getEquipmentStatus(@NotNull @Valid
                                                              @Pattern(regexp = "^imei-[0-9]{15}|imeisv-[0-9]{16}|mac-([0-9a-fA-F]{2})((-[0-9a-fA-F]{2}){5})$")
                                                              @RequestParam(value = "pei", required = true)
                                                                      String pei,
                                                              @Pattern(regexp = "^(imsi-[0-9]{5,15}|nai-.+|.+)$")
                                                              @Valid
                                                              @RequestParam(value = "supi", required = false)
                                                                      String supi,
                                                              @Pattern(regexp = "^(msisdn-[0-9]{5,15}|extid-[^@]+@[^@]+|.+)$")
                                                              @Valid
                                                              @RequestParam(value = "gpsi", required = false)
                                                                      String gpsi) {
        EirResponseData data = null;
        return ResponseEntity.ok(data);
    }
}
