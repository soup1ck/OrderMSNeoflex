package ru.neoflex.conveyor.service.ratecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.conveyor.data.dto.EmploymentDTO;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.validator.scoring.AgeValidator;
import ru.neoflex.conveyor.validator.scoring.WorkExperienceValidator;

import static org.junit.jupiter.api.Assertions.*;

class WorkExperienceScoringRateCalculatorTest {

    private final WorkExperienceValidator workExperienceValidator = Mockito.mock(WorkExperienceValidator.class);
    private final WorkExperienceScoringRateCalculator workExperienceScoringRateCalculator =
            new WorkExperienceScoringRateCalculator(workExperienceValidator);

    @Test
    @DisplayName(value = "Вычисление ставки по опыту работы")
    void calculateRate() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setWorkExperienceTotal(12);
        employmentDTO.setWorkExperienceCurrent(4);
        scoringDataDTO.setEmployment(employmentDTO);

        Mockito.when(workExperienceValidator.validate(scoringDataDTO)).thenReturn(true);

        Integer calculatedRate = workExperienceScoringRateCalculator.calculateRate(scoringDataDTO);

        Assertions.assertEquals(0, calculatedRate);
    }

    @Test
    @DisplayName(value = "Вычисление ставки по опыту работы с ex")
    void calculateRateWithEx() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        EmploymentDTO employmentDTO = new EmploymentDTO();
        employmentDTO.setWorkExperienceTotal(10);
        employmentDTO.setWorkExperienceCurrent(2);
        scoringDataDTO.setEmployment(employmentDTO);

        Mockito.when(workExperienceValidator.validate(scoringDataDTO)).thenReturn(false);

        Assertions.assertThrows(RefusalException.class, () ->
                workExperienceScoringRateCalculator.calculateRate(scoringDataDTO));
    }
}