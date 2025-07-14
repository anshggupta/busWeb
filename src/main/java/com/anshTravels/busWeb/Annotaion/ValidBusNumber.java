package com.anshTravels.busWeb.Annotaion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BusNumberValidator.class)
public @interface ValidBusNumber {

    String message() default "Invalid bus number format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
