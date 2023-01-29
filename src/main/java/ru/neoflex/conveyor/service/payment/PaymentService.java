package ru.neoflex.conveyor.service.payment;

import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.PaymentScheduleElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    private final static BigDecimal BIG_DECIMAL_1200 = new BigDecimal("1200");
    private final static BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");

    public List<PaymentScheduleElement> createPaymentSchedule(Integer term,
                                                              BigDecimal amount,
                                                              BigDecimal rate) {
        LocalDate startDate = LocalDate.now().plusDays(1);
        BigDecimal monthlyPayment = calculateMonthlyPayment(amount, rate, term);
        List<PaymentScheduleElement> paymentSchedule = new ArrayList<>();
        for (int i = 1; i <= term; i++) {
            BigDecimal interestPayment = calculateInterestPayment(rate, amount, startDate);
            BigDecimal debtPayment = monthlyPayment.subtract(interestPayment);
            amount = amount.subtract(debtPayment);
            PaymentScheduleElement paymentScheduleElement = PaymentScheduleElement.builder()
                    .number(i)
                    .date(startDate)
                    .totalPayment(monthlyPayment)
                    .interestPayment(interestPayment)
                    .debtPayment(debtPayment)
                    .remainingDebt(amount)
                    .build();
            startDate = startDate.plusMonths(1);
            paymentSchedule.add(paymentScheduleElement);
        }
        return paymentSchedule;
    }

    public BigDecimal calculateMonthlyPayment(BigDecimal totalAmount, BigDecimal rate, Integer term) {
        BigDecimal monthlyRate = rate.divide(BIG_DECIMAL_1200, 10, RoundingMode.FLOOR);
        BigDecimal onePlusMonthlyRatePowTerm = (BigDecimal.ONE.add(monthlyRate)).pow(term);
        BigDecimal monthlyPayment = totalAmount
                .multiply((monthlyRate.multiply(
                        onePlusMonthlyRatePowTerm))
                        .divide
                                (onePlusMonthlyRatePowTerm
                                                .subtract(BigDecimal.ONE),
                                        4, RoundingMode.FLOOR))
                .setScale(2, RoundingMode.FLOOR);
        return monthlyPayment;
    }

    private BigDecimal calculateInterestPayment(BigDecimal rate, BigDecimal amount,
                                                LocalDate startDate) {
        BigDecimal rateInDecimalForm = rate.divide(BIG_DECIMAL_100, 10, RoundingMode.FLOOR);
        BigDecimal interestPayment = (amount.multiply(rateInDecimalForm))
                .multiply(BigDecimal.valueOf(startDate.lengthOfMonth())
                        .divide(BigDecimal
                                .valueOf(startDate.lengthOfYear()), 4, RoundingMode.CEILING))
                .setScale(2, RoundingMode.FLOOR);
        return interestPayment;
    }
}
