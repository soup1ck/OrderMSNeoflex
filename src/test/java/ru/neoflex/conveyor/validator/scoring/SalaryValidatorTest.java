package ru.neoflex.conveyor.validator.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalaryValidatorTest {

    private final SalaryValidator salaryValidator = new SalaryValidator();

    @Test
    @DisplayName(value = "Валидация зарплаты успешная")
    void validate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setSalary(BigDecimal.TEN);
        scoringDataDTO.setEmployment(employmentDTO);
        scoringDataDTO.setAmount(BigDecimal.TEN);

        Boolean validate = salaryValidator.validate(scoringDataDTO);

        Assertions.assertTrue(validate);
    }

    @Test
    @DisplayName(value = "Валидация зарплаты неуспешная")
    void validateFalse() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setSalary(BigDecimal.TEN);
        scoringDataDTO.setEmployment(employmentDTO);
        scoringDataDTO.setAmount(new BigDecimal("100000"));

        Boolean validate = salaryValidator.validate(scoringDataDTO);

        Assertions.assertFalse(validate);
    }
}