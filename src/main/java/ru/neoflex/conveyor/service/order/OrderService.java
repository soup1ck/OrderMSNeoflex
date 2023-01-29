package ru.neoflex.conveyor.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.LoanApplicationRequestDTO;
import ru.neoflex.conveyor.data.dto.LoanOfferDTO;
import ru.neoflex.conveyor.service.payment.PaymentService;
import ru.neoflex.conveyor.service.scoring.ScoringService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ScoringService scoringService;
    private final PaymentService paymentService;

    public List<LoanOfferDTO> getOffers(LoanApplicationRequestDTO request) {
        List<LoanOfferDTO> loanOfferDTOs = new ArrayList<>();
        loanOfferDTOs.add(createOffer(request, false, false));
        loanOfferDTOs.add(createOffer(request, false, true));
        loanOfferDTOs.add(createOffer(request, true, false));
        loanOfferDTOs.add(createOffer(request, true, true));
        return loanOfferDTOs;
    }


    private LoanOfferDTO createOffer(LoanApplicationRequestDTO request,
                                     Boolean isInsuranceEnabled,
                                     Boolean isSalaryClient) {
        Integer term = request.getTerm();
        BigDecimal requestedAmount = request.getAmount();
        BigDecimal calculatedRate = scoringService.calculateRate(isInsuranceEnabled, isSalaryClient);
        BigDecimal totalAmount = scoringService.calculateTotalAmount(requestedAmount,
                isInsuranceEnabled, isSalaryClient);
        BigDecimal monthlyPayment = paymentService.calculateMonthlyPayment(totalAmount,calculatedRate,term);

        return LoanOfferDTO.builder()
                .requestedAmount(requestedAmount)
                .totalAmount(totalAmount)
                .term(term)
                .monthlyPayment(monthlyPayment)
                .rate(calculatedRate)
                .isInsuranceEnabled(isInsuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .build();
    }
}
