package com.peltops.nestling.controller;

import com.peltops.nestling.dto.InvalidParam;
import com.peltops.nestling.dto.ProblemDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class EquipmentStatusControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProblemDetails onConstraintValidationException(ConstraintViolationException e) {
        ProblemDetails problemDetails = new ProblemDetails();
        List<InvalidParam> invalidParamList = new ArrayList<InvalidParam>();
        for (ConstraintViolation violation:e.getConstraintViolations()) {
            InvalidParam invalidParam = new InvalidParam();
            invalidParam.setParam(violation.getPropertyPath().toString());
            invalidParam.setReason(violation.getMessage());
            invalidParamList.add(invalidParam);
        }
        problemDetails.setInvalidParams(invalidParamList);
        return problemDetails;
    }
}
