package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.EmploymentStatus;
import ru.neoflex.conveyor.exception.RefusalException;

class EmploymentStatusScoringRateCalculatorTest {

    private final EmploymentStatusScoringRateCalculator employmentStatusScoringRateCalculator =
            new EmploymentStatusScoringRateCalculator();

    @Test
    @DisplayName(value = "Вычисление ставки по рабочему статусу")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        scoringDataDTO.setEmployment(employmentDTO);
        Integer calculatedRate = employmentStatusScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(3, calculatedRate);
    }

    @Test
    @DisplayName(value = "Вычисление ставки по рабочему статусу с ошибкой")
    void calculateRateWithEx() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
        scoringDataDTO.setEmployment(employmentDTO);
        String expectedMessage = "Отказ, так как Ваш рабочий статус UNEMPLOYED";
        RefusalException refusalException = Assertions.assertThrows(RefusalException.class, () ->
                employmentStatusScoringRateCalculator.calculateRate(scoringDataDTO));
        Assertions.assertTrue(expectedMessage.contains(refusalException.getMessage()));
    }
}