package ru.neoflex.conveyor.validator.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.Gender;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AgeValidatorTest {

    private final AgeValidator ageValidator = new AgeValidator();

    @Test
    @DisplayName(value = "Валидация возраста успешная")
    void validate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthDate(LocalDate.of(1990, 1, 1));

        Boolean validate = ageValidator.validate(scoringDataDTO);

        Assertions.assertTrue(validate);
    }

    @Test
    @DisplayName(value = "Валидация возраста неуспешная")
    void validateFalse() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthDate(LocalDate.of(2022, 1, 1));

        Boolean validate = ageValidator.validate(scoringDataDTO);

        Assertions.assertFalse(validate);
    }
}