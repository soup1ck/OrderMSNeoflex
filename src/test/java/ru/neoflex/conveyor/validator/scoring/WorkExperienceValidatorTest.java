package ru.neoflex.conveyor.validator.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

import static org.junit.jupiter.api.Assertions.*;

class WorkExperienceValidatorTest {

    private final WorkExperienceValidator workExperienceValidator = new WorkExperienceValidator();

    @Test
    @DisplayName(value = "Валидация по опыту работы успешная")
    void validate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setWorkExperienceCurrent(12);
        employmentDTO.setWorkExperienceTotal(13);
        scoringDataDTO.setEmployment(employmentDTO);

        Boolean validate = workExperienceValidator.validate(scoringDataDTO);

        Assertions.assertTrue(validate);
    }

    @Test
    @DisplayName(value = "Валидация по опыту работы неуспешная")
    void validateFalse() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setWorkExperienceCurrent(2);
        employmentDTO.setWorkExperienceTotal(3);
        scoringDataDTO.setEmployment(employmentDTO);

        Boolean validate = workExperienceValidator.validate(scoringDataDTO);

        Assertions.assertFalse(validate);
    }
}