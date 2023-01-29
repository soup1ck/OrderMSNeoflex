package ru.neoflex.conveyor.utils.validator.scoring;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

@Service
public class WorkExperienceValidator implements ValidateCommand{

    @Override
    public Boolean validate(ScoringDataDTO scoringDataDTO) {
        Integer workExperienceTotal = scoringDataDTO.getEmployment().getWorkExperienceTotal();
        Integer workExperienceCurrent = scoringDataDTO.getEmployment().getWorkExperienceCurrent();
        return workExperienceTotal >= 12 && workExperienceCurrent >= 3;
    }
}
