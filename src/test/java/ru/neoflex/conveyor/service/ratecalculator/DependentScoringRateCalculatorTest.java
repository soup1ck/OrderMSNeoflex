package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

class DependentScoringRateCalculatorTest {

    private final DependentScoringRateCalculator dependentScoringRateCalculator =
            new DependentScoringRateCalculator();

    @Test
    @DisplayName(value = "Тестирование вычисления ставки по иждевенцам")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setDependentAmount(0);
        Integer calculatedRate = dependentScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(0, calculatedRate);
        ScoringDataDTO scoringDataDTOOver1 = new ScoringDataDTO();
        scoringDataDTOOver1.setDependentAmount(2);
        Integer calculatedRateOver1 = dependentScoringRateCalculator.calculateRate(scoringDataDTOOver1);
        Assertions.assertEquals(1, calculatedRateOver1);
    }
}