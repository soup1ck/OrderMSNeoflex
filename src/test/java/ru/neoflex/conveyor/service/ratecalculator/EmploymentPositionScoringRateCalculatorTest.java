package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.EmploymentPosition;

class EmploymentPositionScoringRateCalculatorTest {

    private final EmploymentPositionScoringRateCalculator employmentPositionScoringRateCalculator =
            new EmploymentPositionScoringRateCalculator();

    @Test
    @DisplayName(value = "Тестирование ставки по позиции работника")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setPosition(EmploymentPosition.TOP_MANAGER);
        scoringDataDTO.setEmployment(employmentDTO);
        Integer calculatedRate = employmentPositionScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(-4, calculatedRate);
    }
}