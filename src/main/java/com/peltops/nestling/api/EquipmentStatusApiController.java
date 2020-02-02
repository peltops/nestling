package com.peltops.nestling.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-01-30T23:59:32.760708+01:00[Europe/Berlin]")

@Controller
@RequestMapping("${openapi.5gEIREquipmentIdentityCheck.base-path:/n5g-eir-eic/v1}")
public class EquipmentStatusApiController implements EquipmentStatusApi {

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public EquipmentStatusApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
