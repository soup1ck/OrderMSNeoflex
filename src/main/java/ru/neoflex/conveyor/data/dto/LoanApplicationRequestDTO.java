package ru.neoflex.conveyor.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.neoflex.conveyor.validator.birthdate.BirthDay;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Schema(description = "Запрос кредита")
public class LoanApplicationRequestDTO {

    @NotNull
    @DecimalMin(value = "10000")
    @Schema(description = "Запрашиваемая сумма")
    private BigDecimal amount;

    @NotNull
    @Min(value = 6L)
    @Schema(description = "Кол-во месяцев")
    private Integer term;

    @Schema(description = "Имя")
    @NotEmpty(message = "Поле имя обязательно для заполнения")
    @Size(min = 2, max = 30, message = "Длина имени от 2 до 30 символов")
    private String firstName;

    @Schema(description = "Фамилия")
    @NotEmpty(message = "Поле фамилия обязательно для заполнения")
    @Size(min = 2, max = 30, message = "Длина фамилии от 2 до 30 символов")
    private String lastName;

    @Schema(description = "Отчество")
    @Size(min = 2, max = 30, message = "Длина отчества от 2 до 30 символов")
    private String middleName;

    @Schema(description = "Почта")
    @NotEmpty(message = "Поле email обязательно для заполнения")
    @Email(regexp = "[\\w\\.]{2,50}@[\\w\\.]{2,20}")
    private String email;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Дата рождения")
    @BirthDay(message = "Возраст должен быть больше или равен 18 лет")
    private LocalDate birthDate;

    @Pattern(regexp = "\\d{4}")
    @Schema(description = "Серия паспорта")
    @NotEmpty(message = "Поле серия паспорта обязательно для заполнения")
    private String passportSeries;

    @Pattern(regexp = "\\d{6}")
    @Schema(description = "Номер паспорта")
    @NotEmpty(message = "Поле номер паспорта обязательно для заполнения")
    private String passportNumber;

}
