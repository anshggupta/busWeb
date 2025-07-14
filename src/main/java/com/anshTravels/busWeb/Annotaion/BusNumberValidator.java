package com.anshTravels.busWeb.Annotaion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BusNumberValidator implements ConstraintValidator<ValidBusNumber, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("Validating bus number");

        if (value == null || value.isBlank()) return false;

        // Example pattern: UP16QR8888 (2 letters, 2 digits, 2 letters, 4 digits)
        return value.matches("^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$");
    }
}
