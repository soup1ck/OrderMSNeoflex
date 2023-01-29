package ru.neoflex.conveyor.validator.scoring;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;

import java.math.BigDecimal;

@Service
public class SalaryValidator implements ValidateCommand {

    @Override
    public Boolean validate(ScoringDataDTO scoringDataDTO) {
        BigDecimal amount = scoringDataDTO.getAmount();
        BigDecimal salary = scoringDataDTO.getEmployment().getSalary();
        return amount.compareTo(salary.multiply(BigDecimal.valueOf(20))) < 0;
    }
}
