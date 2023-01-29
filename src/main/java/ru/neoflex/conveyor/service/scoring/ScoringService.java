package ru.neoflex.conveyor.service.scoring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.service.payment.PaymentService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ScoringService {

    @Value("${BASE_RATE}")
    private BigDecimal baseRate;
    private static final BigDecimal BIG_DECIMAL_0_5 = new BigDecimal("0.5");
    private static final BigDecimal BIG_DECIMAL_0_25 = new BigDecimal("0.25");
    private static final BigDecimal BIG_DECIMAL_5000 = new BigDecimal("5000");
    private static final BigDecimal BIG_DECIMAL_3000 = new BigDecimal("3000");
    private static final BigDecimal BIG_DECIMAL_10000 = new BigDecimal("10000");

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
}
