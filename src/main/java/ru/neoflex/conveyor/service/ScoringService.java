package ru.neoflex.conveyor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.CreditDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ScoringService {

    @Value("${BASE_RATE}")
    private BigDecimal baseRate;
    private static final BigDecimal BIG_DECIMAL_0_5 = new BigDecimal("0.5");
    private static final BigDecimal BIG_DECIMAL_0_25 = new BigDecimal("0.25");
    private static final BigDecimal BIG_DECIMAL_5000 = new BigDecimal("5000");
    private static final BigDecimal BIG_DECIMAL_3000 = new BigDecimal("3000");
    private static final BigDecimal BIG_DECIMAL_10000 = new BigDecimal("10000");
    private final static BigDecimal BIG_DECIMAL_1200 = new BigDecimal("1200");

    public BigDecimal calculateRate(Boolean isInsuranceEnabled,
                                    Boolean isSalaryClient) {
        if (isInsuranceEnabled && isSalaryClient) {
            return baseRate.subtract(BIG_DECIMAL_0_5);
        }
        if (!isInsuranceEnabled && !isSalaryClient) {
            return baseRate.add(BIG_DECIMAL_0_5);
        }
        return baseRate.subtract(BIG_DECIMAL_0_25);
    }

    public BigDecimal calculateTotalAmount(BigDecimal requestedAmount,
                                           Boolean isInsuranceEnabled,
                                           Boolean isSalaryClient) {
        if (isInsuranceEnabled && isSalaryClient) {
            return requestedAmount.add(BIG_DECIMAL_3000);
        }
        if (!isInsuranceEnabled && !isSalaryClient) {
            return requestedAmount.add(BIG_DECIMAL_10000);
        }
        return requestedAmount.add(BIG_DECIMAL_5000);
    }

    public BigDecimal calculateMonthlyPayment(BigDecimal totalAmount, BigDecimal rate, Integer term) {
        BigDecimal monthlyRate = rate.divide(BIG_DECIMAL_1200, 10, RoundingMode.FLOOR);
        BigDecimal onePlusMonthlyRatePowTerm = (BigDecimal.ONE.add(monthlyRate)).pow(term);
        return totalAmount.multiply(
                (monthlyRate.multiply(
                        onePlusMonthlyRatePowTerm)
                ).divide
                        (onePlusMonthlyRatePowTerm
                                        .subtract(BigDecimal.ONE),
                                2, RoundingMode.FLOOR));
    }
}
