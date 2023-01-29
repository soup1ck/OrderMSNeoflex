package ru.neoflex.conveyor.service.credit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.CreditDTO;
import ru.neoflex.conveyor.data.dto.PaymentScheduleElement;
import ru.neoflex.conveyor.data.dto.ScoringDataDTO;
import ru.neoflex.conveyor.exception.RefusalException;
import ru.neoflex.conveyor.service.payment.PaymentService;
import ru.neoflex.conveyor.service.scoring.ScoringRateCalculatingService;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditService {

    private final PaymentService paymentService;
    private final ScoringRateCalculatingService rateCalculatingService;

    public CreditDTO createCreditDTO(ScoringDataDTO request) {
        log.info("Идет процесс формирования данных о кредите");
        log.info("Информация для скоринга: {}", request);
        Boolean isInsuranceEnabled = request.getIsInsuranceEnabled();
        Boolean isSalaryClient = request.getIsSalaryClient();
        BigDecimal amount = request.getAmount();
        Integer term = request.getTerm();
        BigDecimal calculatedRate = rateCalculatingService.calculateForDetails(request);
        BigDecimal monthlyPayment = paymentService.calculateMonthlyPayment(amount, calculatedRate, term);
        List<PaymentScheduleElement> paymentSchedule = paymentService.createPaymentSchedule(term, amount,
                calculatedRate);
        CreditDTO creditDTO = CreditDTO.builder()
                .amount(amount)
                .term(term)
                .monthlyPayment(monthlyPayment)
                .rate(calculatedRate)
                .psk(null)
                .isInsuranceEnabled(isInsuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .paymentSchedule(paymentSchedule)
                .build();
        log.info("Процесс формирования данных о кредите завершен");
        log.info("Информация о кредите: {}", creditDTO);
        return creditDTO;
    }
}
