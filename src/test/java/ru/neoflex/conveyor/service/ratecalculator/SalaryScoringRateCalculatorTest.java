package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.validator.scoring.SalaryValidator;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalaryScoringRateCalculatorTest {

    private final SalaryValidator salaryValidator = Mockito.mock(SalaryValidator.class);
    private final SalaryScoringRateCalculator salaryScoringRateCalculator =
            new SalaryScoringRateCalculator(salaryValidator);

    @Test
    @DisplayName(value = "Вычисление ставки по зарплате")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setSalary(new BigDecimal("100"));
        scoringDataDTO.setEmployment(employmentDTO);

        Mockito.when(salaryValidator.validate(scoringDataDTO)).thenReturn(true);

        Integer calculatedRate = salaryScoringRateCalculator.calculateRate(scoringDataDTO);

        Assertions.assertEquals(0, calculatedRate);
    }

    @Test
    @DisplayName(value = "Вычисление ставки по зарплате с ex")
    void calculateRateWithEx() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setSalary(new BigDecimal("100"));
        scoringDataDTO.setEmployment(employmentDTO);

        Mockito.when(salaryValidator.validate(scoringDataDTO)).thenReturn(false);

        String expectedMessage = "Отказ, так как Ваша зарплата: 100";
        RefusalException refusalException = assertThrows(RefusalException.class, () ->
                salaryScoringRateCalculator.calculateRate(scoringDataDTO));
        Assertions.assertTrue(expectedMessage.contains(refusalException.getMessage()));
    }
}