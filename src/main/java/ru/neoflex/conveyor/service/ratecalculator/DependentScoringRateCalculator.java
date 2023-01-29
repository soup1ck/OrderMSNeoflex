package ru.neoflex.conveyor.service.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

@Service
public class DependentScoringRateCalculator implements ScoringRateCalculator {

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        if (scoringDataDTO.getDependentAmount() > 1) {
            return 1;
        } else return 0;
    }
}
