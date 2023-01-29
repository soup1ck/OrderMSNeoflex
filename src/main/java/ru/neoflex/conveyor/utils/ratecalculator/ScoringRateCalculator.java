package ru.neoflex.conveyor.utils.ratecalculator;

import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;

public interface ScoringRateCalculator {
    Integer calculateRate(ScoringDataDTO scoringDataDTO) throws RefusalException;
}
