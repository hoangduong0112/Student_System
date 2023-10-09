package com.hd.student.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ScheduleOrderValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateScheduleOrder {
    String message() default "tiết bắt đầu phải bé hơn tiết kết thúc";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
