package com.peltops.nestling.controller;

import com.peltops.nestling.dto.CommonError;
import com.peltops.nestling.dto.InvalidParam;
import com.peltops.nestling.dto.ProblemDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class EquipmentStatusControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetails> onConstraintValidationException(ConstraintViolationException e) {
        ProblemDetails problemDetails = new ProblemDetails();
        List<InvalidParam> invalidParamList = new ArrayList<InvalidParam>();
        for (ConstraintViolation violation:e.getConstraintViolations()) {
            InvalidParam invalidParam = new InvalidParam();
            invalidParam.setParam(violation.getPropertyPath().toString());
            invalidParam.setReason(violation.getMessage());
            invalidParamList.add(invalidParam);
        }
        problemDetails.setInvalidParams(invalidParamList);
        return ResponseEntity.badRequest().
                contentType(MediaType.APPLICATION_PROBLEM_JSON).
                body(problemDetails);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetails> onMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ProblemDetails problemDetails = new ProblemDetails();
        List<InvalidParam> invalidParamList = new ArrayList<InvalidParam>();
        InvalidParam invalidParam = new InvalidParam();
        invalidParam.setParam("query: " + e.getParameterName());
        invalidParam.setReason(e.getMessage());
        invalidParamList.add(invalidParam);
        problemDetails.setCause(CommonError.MANDATORY_QUERY_PARAM_MISSING.toString());
        problemDetails.setInvalidParams(invalidParamList);
        return ResponseEntity.badRequest().
                contentType(MediaType.APPLICATION_PROBLEM_JSON).
                body(problemDetails);
    }
}
