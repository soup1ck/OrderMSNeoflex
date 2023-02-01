package ru.neoflex.conveyor.service.ratecalculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.validator.scoring.SalaryValidator;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SalaryScoringRateCalculator implements ScoringRateCalculator {

    private final SalaryValidator salaryValidator;

    @Override
    public Integer calculateRate(ScoringDataDTO scoringDataDTO) {
        BigDecimal salary = scoringDataDTO.getEmployment().getSalary();
        if (!salaryValidator.validate(scoringDataDTO)) {
            throw new RefusalException("Отказ, так как Ваша зарплата: " + salary);
        } else {
            return 0;
        }
    }
}
