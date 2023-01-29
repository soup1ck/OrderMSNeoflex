package ru.neoflex.conveyor.utils.validator.scoring;

import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

public interface ValidateCommand {
    Boolean validate(ScoringDataDTO scoringDataDTO);
}
