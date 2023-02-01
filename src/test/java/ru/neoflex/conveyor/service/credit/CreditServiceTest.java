package ru.neoflex.conveyor.service.credit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.neoflex.conveyor.data.dto.CreditDTO;
import ru.neoflex.conveyor.data.dto.PaymentScheduleElement;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.service.payment.PaymentService;
import ru.neoflex.conveyor.service.scoring.ScoringRateCalculatingService;

import java.math.BigDecimal;
import java.util.List;

class CreditServiceTest {


    private final PaymentService paymentService = Mockito.mock(PaymentService.class);
    private final ScoringRateCalculatingService rateCalculatingService = Mockito.mock(ScoringRateCalculatingService.class);
    private final CreditService creditService = new CreditService(paymentService, rateCalculatingService);
    private final List<PaymentScheduleElement> paymentScheduleElements = Mockito.mock(List.class);

    @Test
    @DisplayName(value = "Тестирование создания CreditDTO")
    public void test() {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(new BigDecimal("10000"));
        scoringDataDTO.setTerm(3);
        BigDecimal calculatedRate = new BigDecimal("7.5");
        BigDecimal calculatedMonthlyPayment = new BigDecimal("3300");

        Mockito.when(rateCalculatingService.calculateForDetails(scoringDataDTO))
                .thenReturn(calculatedRate);
        Mockito.when(paymentService.calculateMonthlyPayment(scoringDataDTO.getAmount(), calculatedRate, scoringDataDTO.getTerm()))
                .thenReturn(calculatedMonthlyPayment);
        Mockito.when(paymentScheduleElements.size()).thenReturn(3);
        Mockito.when(paymentService.createPaymentSchedule(scoringDataDTO.getTerm(), scoringDataDTO.getAmount(), calculatedRate))
                .thenReturn(paymentScheduleElements);

        CreditDTO creditDTO = creditService.createCreditDTO(scoringDataDTO);

        Assertions.assertEquals(calculatedRate, creditDTO.getRate());
        Assertions.assertEquals(3, creditDTO.getPaymentSchedule().size());
        Assertions.assertEquals(calculatedMonthlyPayment, creditDTO.getMonthlyPayment());
    }

}
