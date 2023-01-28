package ru.neoflex.conveyor.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.neoflex.conveyor.utils.BirthDay;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LoanApplicationRequestDTO {

    @NotNull
    @DecimalMin(value = "10000")
    private BigDecimal amount;

    @NotNull
    @Min(value = 6L)
    private Integer term;

    @NotEmpty(message = "Поле имя обязательно для заполнения")
    @Size(min = 2, max = 30, message = "Длина имени от 2 до 30 символов")
    private String firstName;

    @NotEmpty(message = "Поле фамилия обязательно для заполнения")
    @Size(min = 2, max = 30, message = "Длина фамилии от 2 до 30 символов")
    private String lastName;

    @Size(min = 2, max = 30, message = "Длина отчества от 2 до 30 символов")
    private String middleName;

    @NotEmpty(message = "Поле email обязательно для заполнения")
    @Email(regexp = "[\\w\\.]{2,50}@[\\w\\.]{2,20}")
    private String email;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @BirthDay(message = "Возраст должен быть больше или равен 18 лет")
    private LocalDate birthDate;

    @NotEmpty(message = "Поле серия паспорта обязательно для заполнения")
    @Pattern(regexp = "\\d{4}")
    private String passportSeries;

    @NotEmpty(message = "Поле номер паспорта обязательно для заполнения")
    @Pattern(regexp = "\\d{6}")
    private String passportNumber;

}
