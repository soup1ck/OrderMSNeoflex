package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.MaritalStatus;

class MaritalStatusScoringRateCalculatorTest {

    private final MaritalStatusScoringRateCalculator maritalStatusScoringRateCalculator =
            new MaritalStatusScoringRateCalculator();

    @Test
    @DisplayName(value = "Вычисление ставки по семейному положению")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setMaritalStatus(MaritalStatus.DIVORCED);
        Integer calculatedRate = maritalStatusScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(1, calculatedRate);
    }
}