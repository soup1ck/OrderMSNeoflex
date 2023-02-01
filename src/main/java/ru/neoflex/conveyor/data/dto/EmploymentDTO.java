package ru.neoflex.conveyor.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.neoflex.conveyor.data.enums.EmploymentPosition;
import ru.neoflex.conveyor.data.enums.EmploymentStatus;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class EmploymentDTO {

    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private EmploymentPosition position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}
