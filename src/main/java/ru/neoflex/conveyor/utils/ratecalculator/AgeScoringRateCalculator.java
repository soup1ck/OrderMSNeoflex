package ru.neoflex.conveyor.utils.ratecalculator;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class AgeScoringRateCalculator implements ScoringRateCalculator {

    public Integer calculateRate(ScoringDataDTO scoringDataDTO){
        Long age = ChronoUnit.YEARS.between(scoringDataDTO.getBirthDate(), LocalDate.now());
        if (age > 20 && age < 60) {
            return 0;
        } else throw new RefusalException("Отказ, так как Ваш возраст: " + age);
    }
}
