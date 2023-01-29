package ru.neoflex.conveyor.utils.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;

@Service
public class DependentScoringRateCalculator implements ScoringRateCalculator {

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) throws RefusalException {
        if (scoringDataDTO.getDependentAmount() > 1) {
            return 1;
        } else return 0;
    }
}
