package ru.neoflex.conveyor.utils.ratecalculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.utils.validator.scoring.WorkExperienceValidator;

@Service
@RequiredArgsConstructor
public class WorkExperienceScoringRateCalculator implements ScoringRateCalculator {

    private final WorkExperienceValidator workExperienceValidator;

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        Integer workExperienceTotal = scoringDataDTO.getEmployment().getWorkExperienceTotal();
        Integer workExperienceCurrent = scoringDataDTO.getEmployment().getWorkExperienceCurrent();
        if (workExperienceValidator.validate(scoringDataDTO)) {
            return 0;
        } else throw new RefusalException("Отказ, так как Ваш общий стаж: " + workExperienceTotal
                + ", текущий стаж: " + workExperienceCurrent);
    }
}
