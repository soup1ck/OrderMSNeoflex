package ru.neoflex.conveyor.service.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.EmploymentStatus;
import ru.neoflex.conveyor.exception.RefusalException;

import java.util.Map;
import java.util.Optional;

@Service
public class EmploymentStatusScoringRateCalculator implements ScoringRateCalculator {

    private final Map<EmploymentStatus, Integer> mapOfEmploymentStatus = Map.of(
            EmploymentStatus.SELF_EMPLOYED, 1,
            EmploymentStatus.BUSINESS_OWNER, 3,
            EmploymentStatus.EMPLOYED, 0
    );

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        EmploymentStatus employmentStatus = scoringDataDTO
                .getEmployment()
                .getEmploymentStatus();
        return Optional.ofNullable(mapOfEmploymentStatus.get(employmentStatus))
                .orElseThrow(() -> new RefusalException("Отказ, так как Ваш рабочий статус " +
                        employmentStatus));
    }
}
