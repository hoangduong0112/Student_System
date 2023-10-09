package com.hd.student.validation;

import com.hd.student.payload.request.ScheduleInfoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScheduleOrderValidator implements ConstraintValidator<ValidateScheduleOrder, ScheduleInfoRequest> {

    @Override
    public void initialize(ValidateScheduleOrder constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ScheduleInfoRequest scheduleInfoRequest, ConstraintValidatorContext constraintValidatorContext) {
        return scheduleInfoRequest.getStartAt() < scheduleInfoRequest.getEndAt();
    }
}
