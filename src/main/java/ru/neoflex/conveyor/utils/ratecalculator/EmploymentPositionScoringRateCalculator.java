package ru.neoflex.conveyor.utils.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.EmploymentPosition;

import java.util.Map;

@Service
public class EmploymentPositionScoringRateCalculator implements ScoringRateCalculator {

    private final Map<EmploymentPosition, Integer> mapOfEmploymentPosition = Map.of(
            EmploymentPosition.WORKER, 0,
            EmploymentPosition.MID_MANAGER, -2,
            EmploymentPosition.TOP_MANAGER, -4,
            EmploymentPosition.OWNER, 0

    );

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        EmploymentPosition employmentPosition = scoringDataDTO.getEmployment()
                .getPosition();
        return mapOfEmploymentPosition.get(employmentPosition);
    }
}
