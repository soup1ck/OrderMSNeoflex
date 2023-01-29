package ru.neoflex.conveyor.utils.validator.birthdate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.neoflex.conveyor.utils.validator.birthdate.BirthDay;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BirthDayValidator implements ConstraintValidator<BirthDay, LocalDate> {

    private static final int years = 18;

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        return ChronoUnit.YEARS.between(date, LocalDate.now()) >= years;
    }
}
