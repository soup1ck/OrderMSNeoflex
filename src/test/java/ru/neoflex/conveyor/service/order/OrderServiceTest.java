package ru.neoflex.conveyor.service.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.conveyor.data.dto.LoanApplicationRequestDTO;
import ru.neoflex.conveyor.data.dto.LoanOfferDTO;
import ru.neoflex.conveyor.service.payment.PaymentService;
import ru.neoflex.conveyor.service.scoring.ScoringService;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private ScoringService scoringService;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName(value = "Тестирование создания предложений")
    void getOffers() {
        LoanApplicationRequestDTO request = new LoanApplicationRequestDTO();
        BigDecimal amount = new BigDecimal("10000");
        request.setAmount(amount);

        BigDecimal totalAmountAny = new BigDecimal("20000");
        BigDecimal totalAmountTrue = new BigDecimal("13000");
        BigDecimal totalAmountFalse = new BigDecimal("20000");
        BigDecimal defaultMonthlyPayment = new BigDecimal("5000");

        Mockito.when(scoringService.calculateTotalAmount(ArgumentMatchers.any(),
                ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(totalAmountAny);
        Mockito.when(scoringService.calculateTotalAmount(amount, true, true))
                .thenReturn(totalAmountTrue);
        Mockito.when(scoringService.calculateTotalAmount(amount, false, false))
                .thenReturn(totalAmountFalse);

        Mockito.when(paymentService.calculateMonthlyPayment(ArgumentMatchers.any(), ArgumentMatchers.any(),
                        ArgumentMatchers.any()))
                .thenReturn(defaultMonthlyPayment);

        List<LoanOfferDTO> offers = orderService.
                getOffers(request);

        Mockito.verify(scoringService, Mockito.times(4)).calculateRate(ArgumentMatchers.any(),
                ArgumentMatchers.any());
        Mockito.verify(scoringService, Mockito.times(4)).calculateTotalAmount(ArgumentMatchers.any(),
                ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verify(paymentService, Mockito.times(4)).calculateMonthlyPayment(ArgumentMatchers.any(),
                ArgumentMatchers.any(), ArgumentMatchers.any());

        Assertions.assertEquals(4, offers.size());
        Assertions.assertEquals(amount, offers.get(3).getRequestedAmount());
        Assertions.assertEquals(totalAmountAny, offers.get(0).getTotalAmount());
        Assertions.assertEquals(totalAmountTrue, offers.get(3).getTotalAmount());
        Assertions.assertEquals(totalAmountFalse, offers.get(0).getTotalAmount());
        Assertions.assertEquals(defaultMonthlyPayment, offers.get(0).getMonthlyPayment());
    }
}