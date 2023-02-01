package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AgeScoringRateCalculatorTest {

    private final AgeScoringRateCalculator ageScoringRateCalculator =
            new AgeScoringRateCalculator();

    @Test
    @DisplayName("Вычисление ставки по возрасту")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setBirthDate(LocalDate.of(1970, 1, 1));
        Integer calculatedRate = ageScoringRateCalculator.calculateRate(scoringDataDTO);
        Assertions.assertEquals(0, calculatedRate);
    }

    @Test
    @DisplayName("Вычисление ставки по возрасту с ошибкой")
    void calculateRateWithEx() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setBirthDate(LocalDate.now());
        String expectedMessage = "Отказ, так как Ваш возраст: 0";
        RefusalException refusalException = assertThrows(RefusalException.class, () ->
                ageScoringRateCalculator.calculateRate(scoringDataDTO));
        Assertions.assertTrue(expectedMessage.contains(refusalException.getMessage()));
    }
}