package ru.neoflex.conveyor.service.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.MaritalStatus;
import ru.neoflex.conveyor.exception.RefusalException;

import java.util.Map;
import java.util.Optional;

@Service
public class MaritalStatusScoringRateCalculator implements ScoringRateCalculator {

    private final Map<MaritalStatus, Integer> mapOfMaritalStatus = Map.of(
            MaritalStatus.MARRIED, -3,
            MaritalStatus.SINGLE, 0,
            MaritalStatus.DIVORCED, 1,
            MaritalStatus.WIDOW_WIDOWER, 0
    );

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        MaritalStatus maritalStatus = scoringDataDTO.getMaritalStatus();
        return Optional.of(mapOfMaritalStatus.get(maritalStatus))
                .orElseThrow(() -> new RefusalException("Отказ так как ваше семейное положение"
                        + maritalStatus));
    }
}
