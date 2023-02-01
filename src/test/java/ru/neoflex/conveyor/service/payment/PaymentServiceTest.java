package ru.neoflex.conveyor.service.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.conveyor.data.dto.PaymentScheduleElement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    private final PaymentService paymentService = new PaymentService();
    private final List<PaymentScheduleElement> paymentScheduleElements = Mockito.mock(List.class);

    @Test
    @DisplayName(value = "Тестирование создания расписания")
    void createPaymentSchedule() {

        LocalDate startDate = LocalDate.of(2023, 2, 2);
        BigDecimal amount = new BigDecimal("100");
        BigDecimal rate = new BigDecimal("1");
        Integer term = 2;
        List<PaymentScheduleElement> paymentSchedule = paymentService.createPaymentSchedule(term, amount, rate);

        Assertions.assertEquals(startDate, paymentSchedule.get(0).getDate());
        Assertions.assertEquals(LocalDate.of(2023, 3, 2), paymentSchedule.get(1).getDate());
        Assertions.assertEquals(new BigDecimal("50.06"), paymentSchedule.get(0).getTotalPayment());
        Assertions.assertEquals(2, paymentSchedule.size());
        Assertions.assertEquals(new BigDecimal("0.07"),paymentSchedule.get(0).getInterestPayment());
        Assertions.assertEquals(new BigDecimal("49.99"), paymentSchedule.get(0).getDebtPayment());
        Assertions.assertEquals(new BigDecimal("49.94"), paymentSchedule.get(0).getRemainingDebt());
    }

    @Test
    @DisplayName(value = "Вычисление месячного платежа")
    void calculateMonthlyPayment() {
        BigDecimal amount = new BigDecimal("100");
        BigDecimal rate = new BigDecimal("1");
        Integer term = 2;
        BigDecimal monthlyPayment = paymentService.calculateMonthlyPayment(amount, rate, term);
        Assertions.assertEquals(new BigDecimal("50.06"),monthlyPayment);
    }
}