package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.Gender;
import ru.neoflex.conveyor.validator.scoring.AgeValidator;

import static org.junit.jupiter.api.Assertions.*;

class GenderScoringRateCalculatorTest {

    private final AgeValidator ageValidator = Mockito.mock(AgeValidator.class);
    private final GenderScoringRateCalculator genderScoringRateCalculator =
            new GenderScoringRateCalculator(ageValidator);

    @Test
    @DisplayName(value = "Вычисление ставки по гендеру")
    void calculateRate() {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setGender(Gender.MALE);
        Mockito.when(ageValidator.validate(scoringDataDTO)).thenReturn(true);
        Integer calculatedRate = genderScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(-3, calculatedRate);
    }
}