package ru.neoflex.conveyor.service.ratecalculator;

import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

public interface ScoringRateCalculator {
    Integer calculateRate(ScoringDataDTO scoringDataDTO);
}
