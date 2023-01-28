package ru.neoflex.conveyor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.neoflex.conveyor.data.dto.LoanApplicationRequestDTO;
import ru.neoflex.conveyor.data.dto.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ScoringService scoringService;

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

        BigDecimal rate = scoringService.calculateRate(isInsuranceEnabled, isSalaryClient);
        BigDecimal totalAmount = scoringService.calculateTotalAmount(request.getAmount(),
                isInsuranceEnabled, isSalaryClient);
        BigDecimal monthlyPayment = scoringService.calculateMonthlyPayment(totalAmount, rate,
                request.getTerm());

        return LoanOfferDTO.builder()
                .requestedAmount(request.getAmount())
                .totalAmount(totalAmount)
                .term(request.getTerm())
                .monthlyPayment(monthlyPayment)
                .rate(rate)
                .isInsuranceEnabled(isInsuranceEnabled)
                .isSalaryClient(isSalaryClient)
                .build();
    }
}
