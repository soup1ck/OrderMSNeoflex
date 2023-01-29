package ru.neoflex.conveyor.service.scoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.utils.ratecalculator.ScoringRateCalculator;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoringRateCalculatingService {

    private final List<ScoringRateCalculator> scoringRateCalculators;
    private final ScoringService scoringService;

    public BigDecimal calculateForDetails(ScoringDataDTO scoringDataDTO) {
        BigDecimal rate = scoringService.calculateRate(scoringDataDTO.getIsInsuranceEnabled(),
                scoringDataDTO.getIsSalaryClient());
        return rate.add(BigDecimal.valueOf(scoringRateCalculators.stream()
                .map(scoringRateCalculator -> scoringRateCalculator.calculateRate(scoringDataDTO))
                .mapToInt(Integer::intValue)
                .sum()));
    }
}
