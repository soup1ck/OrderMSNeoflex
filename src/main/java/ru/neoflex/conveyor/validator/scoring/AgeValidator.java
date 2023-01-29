package ru.neoflex.conveyor.validator.scoring;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.data.enums.Gender;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class AgeValidator implements ValidateCommand {

    private final Map<Gender, Predicate<Long>> mapOfGender = Map.of(
            Gender.MALE, (Long age) -> age > 29 && age < 56,
            Gender.FEMALE, (Long age) -> age > 34 && age < 61,
            Gender.NON_BINARY, (Long age) -> age > 20 && age < 60
    );

    @Override
    public Boolean validate(ScoringDataDTO scoringDataDTO) {
        Gender gender = scoringDataDTO.getGender();
        Long age = ChronoUnit.YEARS.between(scoringDataDTO.getBirthDate(), LocalDate.now());
        return mapOfGender.get(gender).test(age);
    }
}
