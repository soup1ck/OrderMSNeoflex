package ru.neoflex.conveyor.service.ratecalculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.Gender;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.validator.scoring.AgeValidator;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenderScoringRateCalculator implements ScoringRateCalculator {

    private final AgeValidator ageValidator;

    private final Map<Gender, Integer> mapOfGender = Map.of(
            Gender.MALE, -3,
            Gender.FEMALE, -3,
            Gender.NON_BINARY, 3
    );

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        Gender gender = scoringDataDTO.getGender();
        if (ageValidator.validate(scoringDataDTO)) {
            return Optional.ofNullable(mapOfGender.get(gender))
                    .orElseThrow(() -> new RefusalException("Гендер "
                            + gender
                            + " не найден"));
        } else return 0;
    }
}

