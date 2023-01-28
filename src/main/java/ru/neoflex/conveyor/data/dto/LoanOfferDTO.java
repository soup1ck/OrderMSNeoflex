package ru.neoflex.conveyor.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class LoanOfferDTO {

    public LoanOfferDTO(LoanApplicationRequestDTO loanApplicationRequestDTO,
                        Boolean isInsuranceEnabled,
                        Boolean isSalaryClient) {
        requestedAmount = loanApplicationRequestDTO.getAmount();
        term = loanApplicationRequestDTO.getTerm();
        this.isInsuranceEnabled = isInsuranceEnabled;
        this.isSalaryClient = isSalaryClient;
    }

    private Long applicationId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
}
